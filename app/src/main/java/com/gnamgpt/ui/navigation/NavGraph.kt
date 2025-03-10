package com.gnamgpt.ui.navigation

import CategoryMealsScreen
import RecipeDetailScreen
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
import android.util.Log
import androidx.compose.runtime.remember
import com.gnamgpt.data.UsersDatabase
import com.gnamgpt.ui.theme.GnamGPTTheme
import com.gnamgpt.viewmodel.AssistantViewModel
import com.gnamgpt.viewmodel.UserViewModel

@Composable
fun AppNavGraph(navController: NavHostController) {
    val isUserLoggedIn = FirebaseAuth.getInstance().currentUser != null
    val startDestination = if (isUserLoggedIn) "home" else "login"
    var usersDatabase = remember { UsersDatabase() }
    
    NavHost(navController = navController, startDestination = startDestination) {

        composable("assistant") { AiAssistantScreen() }

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
                onSettingsClick = { navController.navigate("settings") },
                onCategoryClick = { category -> navController.navigate("category/$category") },
                onAssistantClick = { navController.navigate("assistant") }
            )
        }
        
        composable("favorites") {
            FavoritesScreen(
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
            val userViewModel: UserViewModel = viewModel()
            SettingsScreen(
                onChangeLanguage = {  },
                onToggleNotifications = { isEnabled ->  },
                onToggleDarkMode = { isEnabled ->
                    Log.d("SettingsScreen", "Dark mode toggle: $isEnabled")
                    userViewModel.updateTheme(isEnabled)
                }
            )
        }

        composable("category/{categoryName}") { backStackEntry ->
            val categoryName = backStackEntry.arguments?.getString("categoryName") ?: "No Category"
            CategoryMealsScreen(
                categoryName = categoryName,
                onRecipeClick = { recipe -> navController.navigate("recipe/$recipe") },
                onLoginClick = { navController.navigate("login") }
            )
        }
        
        composable("recipe/{recipeId}") { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getString("recipeId") ?: "No Recipe"
            RecipeDetailScreen(
                mealId = recipeId,
                onLoginClick = { navController.navigate("login") },
                usersDatabase = usersDatabase)
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    GnamGPTTheme {
        AppNavGraph(navController = navController)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewApp() {
    val navController = rememberNavController()
    GnamGPTTheme {
        AppNavGraph(navController)
    }
}