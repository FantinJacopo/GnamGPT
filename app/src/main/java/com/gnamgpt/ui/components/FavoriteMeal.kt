package com.gnamgpt.ui.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp

import com.gnamgpt.data.UsersDatabase
import com.google.firebase.auth.FirebaseAuth

@Composable
fun FavoriteMeal(
    recipeId: String,
    usersDatabase: UsersDatabase,
    modifier: Modifier
) {
    var isFavorite by remember { mutableStateOf(false) }

    LaunchedEffect(recipeId) {
        usersDatabase.isRecipeFavorite(recipeId){
            isFavorite = it
        }
    }


    if (FirebaseAuth.getInstance().currentUser == null) return // TODO: Se ho tempo fare pop-up che dica che per salvare i preferiti bisogna loggarsi
    IconButton(
        modifier = modifier,
        onClick = {
            isFavorite = !isFavorite
            if (isFavorite) {
                usersDatabase.addRecipeToFavorites(recipeId)
            } else {
                usersDatabase.removeRecipeFromFavorites(recipeId)
            }
        }
    ) {
        Icon(
            modifier = Modifier.shadow(elevation = 4.dp, shape = CircleShape),
            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
            contentDescription = "Favorite",
            tint = if (isFavorite) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
        )
    }
}