package com.gnamgpt.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AiAssistantScreen() {
    var userInput by remember { mutableStateOf("") }
    var response by remember { mutableStateOf("Ask me anything about cooking!") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(response)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = userInput,
            onValueChange = { userInput = it },
            label = { Text("Ask AI") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { response = "AI is thinking..." }) {
            Text("Ask")
        }
    }
}