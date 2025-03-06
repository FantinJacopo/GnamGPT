package com.gnamgpt.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun ProfileScreen(userName: String, userImage: String, onLogout: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        // Foto profilo
        Image(painter = rememberAsyncImagePainter(userImage), contentDescription = "User Profile Picture", modifier = Modifier.size(100.dp).clip(CircleShape))

        Spacer(modifier = Modifier.height(16.dp))

        // nome
        Text(userName, fontSize = 24.sp, style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(24.dp))

        // Logout
        Button(onClick = onLogout) {
            Text("Log Out")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = { /* TODO: andare a impostazioni */ }) {
            Text("Manage Preferences")
        }
    }
}