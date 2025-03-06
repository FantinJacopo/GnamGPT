package com.gnamgpt.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun RecipeDetailScreen(recipeName: String, recipeImageUrl: String, ingredients: List<String>) {
    val painter = rememberAsyncImagePainter(recipeImageUrl)

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Image(painter = painter, contentDescription = "Recipe Image")
        Spacer(modifier = Modifier.height(16.dp))
        Text(recipeName, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Ingredients:", fontSize = 18.sp, fontWeight = FontWeight.Medium)
        for (ingredient in ingredients) {
            Text("- $ingredient")
        }
    }
}