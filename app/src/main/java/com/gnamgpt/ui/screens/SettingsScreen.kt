package com.gnamgpt.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SettingsScreen(
    onChangeLanguage: () -> Unit,
    onToggleNotifications: (Boolean) -> Unit,
    onToggleDarkMode: (Boolean) -> Unit
) {
    var isNotificationsEnabled by remember { mutableStateOf(true) }
    var isDarkModeEnabled by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            "Settings", 
            fontSize = 28.sp, 
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground
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

        // Notifiche
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                "Enable Notifications", 
                modifier = Modifier.weight(1f),
                color = MaterialTheme.colorScheme.onBackground
            )
            Switch(
                checked = isNotificationsEnabled,
                onCheckedChange = { isNotificationsEnabled = it; onToggleNotifications(it) }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Modalità scura
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Enable Dark Mode", modifier = Modifier.weight(1f))
            Switch(
                checked = isDarkModeEnabled,
                onCheckedChange = { isDarkModeEnabled = it; onToggleDarkMode(it) }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Info app
        TextButton(onClick = {  }) {
            Text("About this App")
        }
    }
}
