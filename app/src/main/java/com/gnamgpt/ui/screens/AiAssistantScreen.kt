package com.gnamgpt.ui.screens

import android.annotation.SuppressLint
import android.text.Html
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gnamgpt.BuildConfig
import com.gnamgpt.model.Message
import com.gnamgpt.viewmodel.AssistantViewModel
import com.gnamgpt.viewmodel.AssistantViewModelFactory

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AiAssistantScreen() {
    val apiKey = BuildConfig.API_KEY
    var showAnimation by remember { mutableStateOf(false) }

    val viewModel: AssistantViewModel = viewModel(factory = AssistantViewModelFactory(apiKey))
    val messages by viewModel.messages.collectAsState()
    var userInput by remember { mutableStateOf("") }
    val listState = rememberLazyListState()

    LaunchedEffect(messages.size) {
        if (messages.isNotEmpty()) {
            listState.animateScrollToItem(messages.size - 1)
        }
    }

    Scaffold {
        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                items(messages) { message ->
                    MessageBubble(message)
                    Spacer(modifier = Modifier.height(8.dp))
                }
                if (showAnimation) {
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = userInput,
                    onValueChange = { userInput = it },
                    modifier = Modifier
                        .weight(1f),
                    placeholder = { Text("Chiedi qualcosa sul cibo...") },
                    maxLines = 3
                )

                Spacer(modifier = Modifier.width(8.dp))

                IconButton(
                    onClick = {
                        if (userInput.isNotBlank()) {
                            viewModel.sendMessage(userInput){ showAnimation = false }
                            userInput = ""
                            showAnimation = true
                        }
                    },
                    enabled = userInput.isNotBlank()
                ) {
                    Icon(imageVector = Icons.Default.Send, contentDescription = "Invia")
                }
            }
        }
    }
}

@Composable
fun MessageBubble(message: Message) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = if (message.isFromUser) Alignment.CenterEnd else Alignment.CenterStart
    ) {
        Surface(
            shape = RoundedCornerShape(
                topStart = 16.dp,
                topEnd = 16.dp,
                bottomStart = if (message.isFromUser) 16.dp else 0.dp,
                bottomEnd = if (message.isFromUser) 0.dp else 16.dp
            ),
            color = if (message.isFromUser)
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.secondaryContainer,
            modifier = Modifier.widthIn(max = 280.dp)
        ) {
            if (message.isFromUser) {
                Text(
                    text = message.text,
                    modifier = Modifier.padding(12.dp),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            } else {
                val context = LocalContext.current
                val textColor = MaterialTheme.colorScheme.onSecondaryContainer.toArgb() // Ottieni il colore qui
                AndroidView(
                    factory = { android.widget.TextView(context) },
                    update = { textView ->
                        textView.text = Html.fromHtml(message.text, Html.FROM_HTML_MODE_LEGACY)
                        textView.setTextColor(textColor)
                        textView.setPadding(
                            12.dp.value.toInt(),
                            12.dp.value.toInt(),
                            12.dp.value.toInt(),
                            12.dp.value.toInt()
                        )
                    },
                    modifier = Modifier.padding(12.dp)
                )
            }
        }
    }
}