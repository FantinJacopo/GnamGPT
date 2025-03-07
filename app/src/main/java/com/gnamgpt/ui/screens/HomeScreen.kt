package com.gnamgpt.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gnamgpt.R

@Composable
fun HomeScreen(
    onRecipeClick: (String) -> Unit,
    onFavoritesClick: () -> Unit,
    onProfileClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        // Barra superiore con immagine profilo e icona impostazioni
        TopAppBar(
            onProfileClick = onProfileClick,
            onSettingsClick = onSettingsClick
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Titolo della home
        Text(
            text = "Ciao, pronto a cucinare?",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
        )

        // Sezione ricette
        RecipeList(onRecipeClick = onRecipeClick)

        // Pulsante Preferiti
        Button(
            onClick = onFavoritesClick,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Icon(Icons.Filled.Favorite, contentDescription = "Preferiti")
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Vai ai preferiti")
        }
    }
}

// Barra superiore con immagine profilo e impostazioni
@Composable
fun TopAppBar(
    onProfileClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Immagine profilo cliccabile
        Image(
            painter = painterResource(id = R.drawable.profile_placeholder),
            contentDescription = "Profilo",
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .clickable { onProfileClick() }
        )

        Spacer(modifier = Modifier.weight(1f))

        // Icona impostazioni
        IconButton(onClick = onSettingsClick) {
            Icon(Icons.Filled.Settings, contentDescription = "Impostazioni")
        }
    }
}

// Lista di ricette (esempio semplificato)
@Composable
fun RecipeList(onRecipeClick: (String) -> Unit) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        repeat(3) { index ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { onRecipeClick("Ricetta_$index") },
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    text = "Ricetta ${index + 1}",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen(
        onRecipeClick = {},
        onFavoritesClick = {},
        onProfileClick = {},
        onSettingsClick = {}
    )
}