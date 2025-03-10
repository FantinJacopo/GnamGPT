package com.gnamgpt.ui.screens

import android.annotation.SuppressLint
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
import com.gnamgpt.ui.components.RecipeItem
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavoritesScreen(
    onRecipeClick: (String) -> Unit,
    favoritesViewModel: FavoritesViewModel = viewModel()
) {
    Scaffold {
        val favoriteMeals by favoritesViewModel.favoriteMeals.collectAsState()
        val usersDatabase = remember { UsersDatabase() }

        LaunchedEffect(Unit) {
            favoritesViewModel.loadFavoriteMeals()
        }

        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text("Favorite Recipes", style = MaterialTheme.typography.headlineLarge)

            Spacer(modifier = Modifier.height(16.dp))

            if (Firebase.auth.currentUser == null) {
                Text("Log in to save your favorite recipes.")
            } else if (favoriteMeals.isNullOrEmpty()) {
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
}