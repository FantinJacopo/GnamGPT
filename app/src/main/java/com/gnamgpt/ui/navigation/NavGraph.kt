package com.gnamgpt.ui.navigation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gnamgpt.ui.screens.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gnamgpt.viewmodel.AuthViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AppNavGraph(navController: NavHostController) {
    val isUserLoggedIn = FirebaseAuth.getInstance().currentUser != null
    val startDestination = if (isUserLoggedIn) "home" else "login"
    
    NavHost(navController = navController, startDestination = startDestination) {
        composable("login") {
            LoginScreen {
                navController.navigate("home") { 
                    popUpTo("login") { inclusive = true } 
                }
            }
        }
        
        composable("home") {
            val authViewModel: AuthViewModel = viewModel()
            
            HomeScreen(
                authViewModel = authViewModel,
                onRecipeClick = { recipe -> navController.navigate("recipe/$recipe") },
                onFavoritesClick = { navController.navigate("favorites") },
                onProfileClick = { 
                    if (FirebaseAuth.getInstance().currentUser != null) {
                        navController.navigate("profile")
                    } else {
                        navController.navigate("login")
                    }
                },
                onSettingsClick = { navController.navigate("settings") }
            )
        }
        
        composable("favorites") {
            FavoritesScreen(
                favoriteRecipes = listOf("Recipe 1", "Recipe 2"), // Esempio per vedere se funziona
                onRemoveFavorite = {  },
                onRecipeClick = { recipe -> navController.navigate("recipe/$recipe") }
            )
        }
        
        composable("profile") {
            val authViewModel: AuthViewModel = viewModel()
            val user = FirebaseAuth.getInstance().currentUser
            
            ProfileScreen(
                userName = user?.displayName ?: "Utente",
                userEmail = user?.email ?: "",
                userImage = user?.photoUrl?.toString() ?: "https://example.com/image.jpg",
                authViewModel = authViewModel,
                onLogout = { 
                    authViewModel.logout()
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true }
                    }
                },
                onManagePreferences = { navController.navigate("settings") }
            )
        }
        
        composable("settings") {
            SettingsScreen(
                onChangeLanguage = {  },
                onToggleNotifications = { isEnabled ->  },
                onToggleDarkMode = { isEnabled ->  }
            )
        }
        
        composable("recipe/{recipeName}/{recipeImage}/{ingredients}") { backStackEntry ->
            val recipeName = backStackEntry.arguments?.getString("recipeName") ?: "No Name"
            val recipeImageUrl = backStackEntry.arguments?.getString("recipeImage") ?: ""
            val ingredientsList = backStackEntry.arguments?.getString("ingredients")?.split(",") ?: emptyList()

            RecipeDetailScreen(recipeName = recipeName, recipeImageUrl = recipeImageUrl, ingredients = ingredientsList)
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    MaterialTheme {
        AppNavGraph(navController = navController)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewApp() {
    val navController = rememberNavController()
    MaterialTheme {
        AppNavGraph(navController)
    }
}