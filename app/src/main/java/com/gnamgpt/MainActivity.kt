package com.gnamgpt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.gnamgpt.ui.screens.HomeScreen
import com.gnamgpt.ui.screens.LoginScreen
import com.gnamgpt.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController, startDestination = "login") {
                composable("login") {
                    LoginScreen {
                        navController.navigate("home") { popUpTo("login") { inclusive = true } }
                    }
                }
                composable("home") {
                    val authViewModel: AuthViewModel = viewModel()

                    HomeScreen(
                        onProfileClick = { navController.navigate("login") },
                        authViewModel = authViewModel,
                        onSettingsClick = { navController.navigate("settings") },
                        onFavoritesClick = { navController.navigate("favorites") },
                        onRecipeClick = { recipeId -> navController.navigate("recipe_detail/$recipeId") }
                    )
                }
            }
        }
    }
}