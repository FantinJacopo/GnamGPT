package com.gnamgpt.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.gnamgpt.R
import androidx.compose.material3.Shapes as Shapes1

// Tema Chiaro
val lightColors = lightColorScheme(
    primary = Primary400, // Esempio di colore primario
    onPrimary = Text100, // Colore per il testo sopra il primary
    secondary = Accent400, // Esempio di colore secondario,
    onSecondary = Text50, // Colore per il testo sopra il secondary,
    background = Background200, // Colore di sfondo
    onBackground = Text100, // Colore per il testo sopra lo sfondo
    surface = Background200, // Superficie chiara
    onSurface = Text100, // Colore per il testo sopra la superficie
    error = Accent400,
    onError = Text950,
)

// Tema Scuro
val darkColors = darkColorScheme(
    primary = Primary400,
    onPrimary = Text900,
    secondary = Accent400,
    onSecondary = Text900,
    background = Background200,
    onBackground = Text900,
    surface = Background900,
    onSurface = Text900,
    error = Accent400,
    onError = Text950,
)

// Applica il tema personalizzato all'app
@Composable
fun GnamGPTTheme(
    isDarkMode: Boolean = true,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (isDarkMode) darkColors else lightColors,
        typography = Typography, // TODO: controllare il font se mi piace e ho tempo
        content = content
    )
}