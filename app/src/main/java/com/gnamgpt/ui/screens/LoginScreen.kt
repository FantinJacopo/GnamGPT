package com.gnamgpt.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gnamgpt.ui.theme.GnamGPTTheme

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    GnamGPTTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("GnamGPT", fontSize = 32.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = { onLoginSuccess() }) {
                Text("Login with Google")       // TODO: Implementare login con google
            }
            Spacer(modifier = Modifier.height(10.dp))
            TextButton(onClick = { onLoginSuccess() }) {
                Text("Continue as Guest")
            }
        }
    }
}
