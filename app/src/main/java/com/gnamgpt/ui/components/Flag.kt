package com.gnamgpt.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.lang.reflect.Modifier

@Composable
fun Flag(
    country: String
) {
    val flagsMap = mapOf(
        "American" to "🇺🇸",
        "British" to "🇬🇧",
        "Canadian" to "🇨🇦",
        "Chinese" to "🇨🇳",
        "Croatian" to "🇭🇷",
        "Dutch" to "🇳🇱",
        "Egyptian" to "🇪🇬",
        "Filipino" to "🇵🇭",
        "French" to "🇫🇷",
        "Greek" to "🇬🇷",
        "Indian" to "🇮🇳",
        "Irish" to "🇮🇪",
        "Italian" to "🇮🇹",
        "Jamaican" to "🇯🇲",
        "Japanese" to "🇯🇵",
        "Kenyan" to "🇰🇪",
        "Malaysian" to "🇲🇾",
        "Mexican" to "🇲🇽",
        "Moroccan" to "🇲🇦",
        "Polish" to "🇵🇱",
        "Portuguese" to "🇵🇹",
        "Russian" to "🇷🇺",
        "Spanish" to "🇪🇸",
        "Thai" to "🇹🇭",
        "Tunisian" to "🇹🇳",
        "Turkish" to "🇹🇷",
        "Ukrainian" to "🇺🇦",
        "Uruguayan" to "🇺🇾",
        "Vietnamese" to "🇻🇳"
    )
    val flag = flagsMap[country] ?: country
    Text(text = flag, fontSize = 20.sp)
}
