package com.gnamgpt.ui.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

import com.gnamgpt.data.UsersDatabase
import com.google.firebase.auth.FirebaseAuth

@Composable
fun FavoriteMeal(
    recipeId: String,
    usersDatabase: UsersDatabase,
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit
) {
    var isFavorite by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(recipeId) {
        usersDatabase.isRecipeFavorite(recipeId) {
            isFavorite = it
        }
    }

    IconButton(
        modifier = modifier,
        onClick = {
            if (FirebaseAuth.getInstance().currentUser == null) {
                showDialog = true
            } else {
                val newFavoriteState = !isFavorite
                isFavorite = newFavoriteState

                if (newFavoriteState) {
                    usersDatabase.addRecipeToFavorites(recipeId)
                } else {
                    usersDatabase.removeRecipeFromFavorites(recipeId)
                }
            }
        }
    ) {
        Icon(
            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
            contentDescription = "Favorite",
            tint = if (isFavorite) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
        )
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Log in to save recipes") },
            text = { Text("Log in to save this recipe to your favorites") },
            confirmButton = {
                Button(onClick = {
                    showDialog = false
                    onLoginClick()
                }) {
                    Text("Log in")
                }
            }
        )
    }
}