package com.gnamgpt.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.gnamgpt.viewmodel.AuthViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun HomeScreen(
    authViewModel: AuthViewModel = viewModel(),
    onProfileClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onFavoritesClick: () -> Unit,
    onRecipeClick: (String) -> Unit
) {
    val user = FirebaseAuth.getInstance().currentUser

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onProfileClick) {
                if (user != null && user.photoUrl != null) {
                    Image(
                        painter = rememberImagePainter(user.photoUrl),
                        contentDescription = "Profilo",
                        modifier = Modifier.size(40.dp)
                    )
                } else {
                    Icon(Icons.Default.Person, contentDescription = "Profilo")
                }
            }
        }

        // Contenuto della Home
        Text("Benvenuto in GnamGPT!", style = MaterialTheme.typography.titleLarge)
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        onRecipeClick = {},
        onFavoritesClick = {},
        onProfileClick = {},
        onSettingsClick = {}
    )
}
