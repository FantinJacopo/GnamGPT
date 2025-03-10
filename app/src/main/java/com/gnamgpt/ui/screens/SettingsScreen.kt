package com.gnamgpt.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gnamgpt.GnamGPTApp
import com.gnamgpt.ui.theme.GnamGPTTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingsScreen(
    onChangeLanguage: () -> Unit,
    onToggleNotifications: (Boolean) -> Unit,
    onToggleDarkMode: (Boolean) -> Unit
) {
    // Ottieni il ViewModel
    val userViewModel = remember { GnamGPTApp.getInstance().userViewModel }

    // Osserva il valore di isDarkMode dal ViewModel
    val isDarkMode by userViewModel.isDarkMode.observeAsState(false)

    // Variabile locale per la modalità scura in Settings
    var isDarkModeEnabled by remember { mutableStateOf(isDarkMode) }

    // Sincronizza la variabile isDarkModeEnabled quando isDarkMode cambia
    LaunchedEffect(isDarkMode) {
        isDarkModeEnabled = isDarkMode
    }

    Scaffold {
        var isNotificationsEnabled by remember { mutableStateOf(true) }

        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text(
                "Settings",
                fontSize = 28.sp,
                style = MaterialTheme.typography.headlineLarge,
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Cambio Lingua
            TextButton(
                onClick = onChangeLanguage,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text("Change Language")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Modalità scura
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Dark Mode", modifier = Modifier.weight(1f))
                Switch(
                    checked = isDarkModeEnabled,
                    onCheckedChange = {
                        isDarkModeEnabled = it
                        onToggleDarkMode(it)
                        userViewModel.updateTheme(it) // Aggiorna anche il ViewModel
                    }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Info app
            TextButton(onClick = {  }) {
                Text("About this App")
            }
        }
    }
}