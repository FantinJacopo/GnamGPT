package com.gnamgpt.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gnamgpt.ui.theme.*

@Composable
fun FavoritesScreen(favoriteRecipes: List<String>, onRemoveFavorite: (String) -> Unit, onRecipeClick: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Favorite Recipes", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(16.dp))

        if (favoriteRecipes.isEmpty()) {
            Text("You have no favorite recipes.")
        } else {
            LazyColumn {
                items(favoriteRecipes) { recipe ->
                    RecipeItem(recipeName = recipe, onClick = { onRecipeClick(recipe) }, onRemoveFavorite = { onRemoveFavorite(recipe) })
                }
            }
        }
    }
}

@Composable
fun RecipeItem(recipeName: String, onClick: () -> Unit, onRemoveFavorite: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Text(recipeName, modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyLarge)
        IconButton(onClick = onClick) {
            Icon(Icons.Default.Info, contentDescription = "View Recipe Details")
        }
        IconButton(onClick = onRemoveFavorite) {
            Icon(Icons.Default.Delete, contentDescription = "Remove from Favorites")
        }
    }
}
