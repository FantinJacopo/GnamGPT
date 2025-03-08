package com.gnamgpt.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.colorResource
import com.gnamgpt.R

// Applica il tema personalizzato all'app
@Composable
fun GnamGPTTheme(
    isDarkMode: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val lightColors = remember {
        lightColorScheme(
            primary = colorResource(R.color.Primary400),
            onPrimary = colorResource(R.color.Text100),
            secondary = colorResource(R.color.Accent400),
            onSecondary = colorResource(R.color.Text50),
            tertiary = colorResource(R.color.Secondary400),
            onTertiary = colorResource(R.color.Text100),
            background = colorResource(R.color.Background200),
            onBackground = colorResource(R.color.Text100),
            surface = colorResource(R.color.Background200),
            onSurface = colorResource(R.color.Text100),
            surfaceVariant = colorResource(R.color.Background300),
            error = colorResource(R.color.Accent400),
            onError = colorResource(R.color.Text950),
            primaryContainer = colorResource(R.color.Primary200),
            onPrimaryContainer = colorResource(R.color.Text100),
            secondaryContainer = colorResource(R.color.Accent200),
            onSecondaryContainer = colorResource(R.color.Text100),
            tertiaryContainer = colorResource(R.color.Secondary200),
            outline = colorResource(R.color.Text300)
        )
    }

    val darkColors = remember {
        darkColorScheme(
            primary = colorResource(R.color.Primary400),
            onPrimary = colorResource(R.color.Text900),
            secondary = colorResource(R.color.Accent400),
            onSecondary = colorResource(R.color.Text900),
            tertiary = colorResource(R.color.Secondary600),
            onTertiary = colorResource(R.color.Text900),
            background = colorResource(R.color.Background200),
            onBackground = colorResource(R.color.Text900),
            surface = colorResource(R.color.Background900),
            onSurface = colorResource(R.color.Text900),
            surfaceVariant = colorResource(R.color.Background700),
            error = colorResource(R.color.Accent400),
            onError = colorResource(R.color.Text950),
            primaryContainer = colorResource(R.color.Primary700),
            onPrimaryContainer = colorResource(R.color.Text800),
            secondaryContainer = colorResource(R.color.Accent700),
            onSecondaryContainer = colorResource(R.color.Text800),
            tertiaryContainer = colorResource(R.color.Secondary700),
            outline = colorResource(R.color.Text600)
        )
    }

    MaterialTheme(
        colorScheme = if (isDarkMode) darkColors else lightColors,
        typography = Typography,
        content = content
    )
}