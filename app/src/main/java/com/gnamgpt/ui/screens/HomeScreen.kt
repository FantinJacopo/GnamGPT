package com.gnamgpt.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onRecipeClick: (String) -> Unit,
    onFavoritesClick: () -> Unit,
    onProfileClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Button(onClick = { onRecipeClick("Recipe 1") }, modifier = Modifier.fillMaxWidth()) {
            Text("View Recipe 1")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { onFavoritesClick() }, modifier = Modifier.fillMaxWidth()) {
            Text("View Favorites")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { onProfileClick() }, modifier = Modifier.fillMaxWidth()) {
            Text("View Profile")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { onSettingsClick() }, modifier = Modifier.fillMaxWidth()) {
            Text("Settings")
        }
    }
}

@Preview
@Composable
fun PreviewHome() {
    HomeScreen(
        onRecipeClick = {},
        onFavoritesClick = {},
        onProfileClick = {},
        onSettingsClick = {}
    )
}