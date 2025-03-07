package com.gnamgpt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.compose.*
import com.gnamgpt.ui.screens.HomeScreen
import com.gnamgpt.ui.screens.LoginScreen

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
                    HomeScreen(
                        onProfileClick = { navController.navigate("login") },
                        authViewModel = TODO(),
                        onSettingsClick = TODO(),
                        onFavoritesClick = TODO(),
                        onRecipeClick = TODO()
                    )
                }
            }
        }
    }
}