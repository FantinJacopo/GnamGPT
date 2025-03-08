package com.gnamgpt

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.gnamgpt.ui.screens.HomeScreen
import com.gnamgpt.ui.screens.LoginScreen
import com.gnamgpt.viewmodel.AuthViewModel
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Prima di impostare il content, verifica lo stato di autenticazione per debug
        FirebaseAuth.getInstance().addAuthStateListener { auth ->
            Log.d("MainActivity", "Auth state changed: ${auth.currentUser != null}")
        }
        
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