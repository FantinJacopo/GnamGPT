package com.gnamgpt.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.gnamgpt.R
import com.gnamgpt.model.Meal
import com.gnamgpt.model.Category
import com.gnamgpt.ui.components.CategoryItem
import com.gnamgpt.ui.components.CategoryMealsSection
import com.gnamgpt.ui.components.GnamGptTopAppBar
import com.gnamgpt.ui.components.RecipeCard
import com.gnamgpt.viewmodel.AuthViewModel
import com.gnamgpt.viewmodel.MealViewModel
import com.google.firebase.auth.FirebaseAuth
import com.gnamgpt.ui.components.SearchBar

@Composable
fun HomeScreen(
    authViewModel: AuthViewModel = viewModel(),
    mealViewModel: MealViewModel = viewModel(),
    onProfileClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onFavoritesClick: () -> Unit,
    onRecipeClick: (String) -> Unit,
    onCategoryClick: (String) -> Unit
) {
    val user = FirebaseAuth.getInstance().currentUser
    
    val categories by mealViewModel.categories.collectAsState()
    val homeCategoryMeals by mealViewModel.homeCategoryMeals.collectAsState()

    LaunchedEffect(Unit) {
        if (categories == null) {
            mealViewModel.loadCategories()
        }
    }

    Scaffold(
        topBar = {
            GnamGptTopAppBar(
                title = "GnamGPT",
                actions = {
                    IconButton(onClick = onSettingsClick) {
                        Icon(
                            Icons.Default.Settings, 
                            contentDescription = "Impostazioni"
                        )
                    }
                    IconButton(onClick = onFavoritesClick) {
                        Icon(Icons.Default.Favorite, contentDescription = "Preferiti")
                    }
                    IconButton(onClick = onProfileClick) {
                        if (user != null && user.photoUrl != null) {
                            AsyncImage(
                                model = user.photoUrl,
                                contentDescription = "Profilo",
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                            )
                        } else {
                            Icon(Icons.Default.Person, contentDescription = "Profilo")
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* TODO: Aggiungere funzionalità AI */ },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(Icons.Filled.Star, contentDescription = "Assistente AI")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                Text(
                    "Ciao${user?.displayName?.let { ", ${it.split(" ").firstOrNull()}" } ?: ""}!",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 16.dp)
                )
                Text(
                    "Cosa vuoi cucinare oggi?",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }
            
            item {
                SearchBar()
            }
            
            item {
                Text(
                    "Categorie",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.padding(vertical = 12.dp)
                ) {
                    categories?.let { cats ->
                        Log.d("HomeScreen", "Categorie da visualizzare: ${cats.size}")
                        items(cats) { category ->
                            CategoryItem(
                                name = category.strCategory,
                                imageUrl = category.strCategoryThumb,
                                onClick = {
                                    onCategoryClick(category.strCategory)
                                }
                            )
                        }
                    } ?: run {
                        Log.d("HomeScreen", "Categorie è null")
                    }
                }
            }

            categories?.forEach { category ->
                item {
                    CategoryMealsSection(
                        category = category.strCategory,
                        meals = homeCategoryMeals[category.strCategory] ?: emptyList(),
                        onSeeAllClick = {
                            mealViewModel.loadAllMealsByCategory(category.strCategory)
                            onCategoryClick(category.strCategory)
                        },
                        onRecipeClick = { recipe ->
                            Log.d("HomeScreen", "onRecipeClick: recipeId = $recipe")
                            onRecipeClick(recipe)
                        }
                    )
                }
            } ?: run {
                Log.d("HomeScreen", "categories è null prima del foreacgh")
            }
            
            item {
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }
}



@Preview
@Composable
fun HomeScreenPreview() {
    /*HomeScreen(
        onRecipeClick = {},
        onFavoritesClick = {},
        onProfileClick = {},
        onSettingsClick = {},
        onCategoryClick = {}
    )*/
}