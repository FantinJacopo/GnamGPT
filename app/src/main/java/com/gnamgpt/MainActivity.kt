package com.gnamgpt

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.gnamgpt.ui.navigation.AppNavGraph
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Prima di impostare il content, verifica lo stato di autenticazione per debug
        FirebaseAuth.getInstance().addAuthStateListener { auth ->
            Log.d("MainActivity", "Auth state changed: ${auth.currentUser != null}")
        }
        
        setContent {
            GnamGPT()
        }
    }
}

@Composable
fun GnamGPT() {
    val navController = rememberNavController()

    MaterialTheme {
        AppNavGraph(navController = navController)
    }
}