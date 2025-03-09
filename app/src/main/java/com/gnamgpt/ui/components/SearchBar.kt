package com.gnamgpt.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun SearchBar(
    onSubmit: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
            onSubmit(it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 8.dp),
        placeholder = { Text("Cerca ricette...") },
        leadingIcon = {
            OutlinedButton (
                onClick = { onSubmit(text) },
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier.size(40.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Cerca"
                )
            }
        },
        trailingIcon = {
            if (text.isNotEmpty()) {
                IconButton(
                    onClick = {
                        text = ""
                        onSubmit("@!_-")  // stringa che sono sicuro non restituirà mai nessun risultato
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "Cancella"
                    )
                }
            }
        },
        shape = RoundedCornerShape(12.dp),
        singleLine = true,
        keyboardActions = KeyboardActions(
            onDone = { onSubmit(text) }
        )
    )
}
