package com.gnamgpt.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.gnamgpt.ui.theme.*
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.gnamgpt.viewmodel.FavoritesViewModel
import com.gnamgpt.data.UsersDatabase

@Composable
fun FavoritesScreen(
    onRecipeClick: (String) -> Unit,
    favoritesViewModel: FavoritesViewModel = viewModel()
) {
    val favoriteMeals by favoritesViewModel.favoriteMeals.collectAsState()
    val usersDatabase = remember { UsersDatabase() }

    LaunchedEffect(Unit) {
        favoritesViewModel.loadFavoriteMeals()
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Favorite Recipes", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(16.dp))

        if (favoriteMeals.isNullOrEmpty()) {
            Text("You have no favorite recipes.")
        } else {
            LazyColumn {
                items(favoriteMeals!!) { meal ->
                    RecipeItem(
                        recipeName = meal.strMeal,
                        imageUrl = meal.strMealThumb ?: "",
                        onClick = { onRecipeClick(meal.idMeal) },
                        onRemoveFavorite = {
                            usersDatabase.removeRecipeFromFavorites(meal.idMeal)
                            favoritesViewModel.loadFavoriteMeals()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun RecipeItem(
    recipeName: String,
    imageUrl: String,
    onClick: () -> Unit,
    onRemoveFavorite: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(4.dp, shape = RoundedCornerShape(12.dp)), // Aggiungi l'ombra
        shape = RoundedCornerShape(12.dp) // Bordo arrotondato
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = "Recipe Image",
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .padding(end = 8.dp)
                    .width(60.dp),
                contentScale = ContentScale.Crop
            )

            Text(
                recipeName,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyLarge
            )

            IconButton(onClick = onClick) {
                Icon(
                    Icons.Default.Info,
                    contentDescription = "View Recipe Details",
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            IconButton(onClick = onRemoveFavorite) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = "Remove from Favorites",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}