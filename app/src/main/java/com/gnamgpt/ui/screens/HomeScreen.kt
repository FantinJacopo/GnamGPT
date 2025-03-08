package com.gnamgpt.ui.screens

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
import com.gnamgpt.viewmodel.AuthViewModel
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    authViewModel: AuthViewModel = viewModel(),
    onProfileClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onFavoritesClick: () -> Unit,
    onRecipeClick: (String) -> Unit
) {
    val user = FirebaseAuth.getInstance().currentUser
    
    // TODO: Sostituire con le ricette reali dall'API
    val popularRecipes = listOf(
        Pair("Pizza Margherita", "https://images.unsplash.com/photo-1604382354936-07c5d9983bd3"),
        Pair("Pasta Carbonara", "https://images.unsplash.com/photo-1612874742237-6526221588e3"),
        Pair("Risotto ai Funghi", "https://images.unsplash.com/photo-1633964913849-0255c8bfdb8a"),
        Pair("Lasagna", "https://images.unsplash.com/photo-1619895092538-128341789043")
    )
    
    // TODO: Sostituire con le categorie reali dall'API
    val categories = listOf(
        Pair("Italiano", Icons.Default.Search),
        Pair("Dolci", Icons.Default.Star),
        Pair("Vegetariano", Icons.Default.Face),
        Pair("Pesce", Icons.Default.Favorite)
    )

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
                Icon(Icons.Default.Add, contentDescription = "Assistente AI")
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
                    items(categories) { category ->
                        CategoryItem(
                            name = category.first,
                            icon = category.second,
                            onClick = {
                                // TODO: Aggiungere cosa deve fare la categoria -> portare alla pagina della visualizzazione per categoria
                            }
                        )
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Ricette Popolari",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    TextButton(
                        onClick = { /* TODO: Aggiungere funzionalità per vedere tutte le ricette */ },
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text("Vedi tutte")
                    }
                }
                
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    items(popularRecipes) { recipe ->
                        RecipeCard(name = recipe.first, imageUrl = recipe.second) {
                            onRecipeClick(recipe.first.replace(" ", "_").lowercase())
                        }
                    }
                }
            }
            
            item {
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }
}

// TODO: SE HO TEMPO: spostare i componesable "secondari" in file a parte, il codice è troppo lungo

@Composable
fun SearchBar() {
    var text by remember { mutableStateOf("") }
    
    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        placeholder = { Text("Cerca ricette...") },
        leadingIcon = { 
            Icon(
                Icons.Default.Search,
                contentDescription = "Cerca"
            ) 
        },
        shape = RoundedCornerShape(12.dp),
        singleLine = true
    )
}

@Composable
fun CategoryItem(
    name: String, 
    icon: ImageVector, 
    onClick: () -> Unit,
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(80.dp)
            .clickable(onClick = onClick)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(containerColor)
        ) {
            Icon(
                icon, 
                contentDescription = name,
                tint = contentColor,
                modifier = Modifier.size(32.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            name,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun RecipeCard(name: String, imageUrl: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(180.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            AsyncImage(
                model = imageUrl,
                contentDescription = name,
                placeholder = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                name,
                modifier = Modifier.padding(12.dp),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GnamGptTopAppBar(
    title: String,
    actions: @Composable RowScope.() -> Unit = {}
) {
    TopAppBar(
        title = { Text(title, fontWeight = FontWeight.Bold) },
        actions = actions
    )
}