package com.gnamgpt

import android.os.Bundle
import android.util.Log
import android.view.Window.FEATURE_NO_TITLE
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.gnamgpt.ui.navigation.AppNavGraph
import androidx.navigation.compose.rememberNavController
import com.gnamgpt.ui.theme.GnamGPTTheme
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
        // Prima di impostare il content, verifica lo stato di autenticazione per debug
        FirebaseAuth.getInstance().addAuthStateListener { auth ->
            Log.d("MainActivity", "Auth state changed: ${auth.currentUser != null}")
        }
        
        setContent {
            GnamGPTTheme {
                GnamGPT()
            }
        }
    }
}

@Composable
fun GnamGPT() {
    val navController = rememberNavController()

    AppNavGraph(navController = navController)
}