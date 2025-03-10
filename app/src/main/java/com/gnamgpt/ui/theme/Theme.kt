package com.gnamgpt.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.livedata.observeAsState
import com.gnamgpt.GnamGPTApp

@Composable
fun GnamGPTTheme(
    content: @Composable () -> Unit
) {
    val userViewModel = remember { GnamGPTApp.getInstance().userViewModel }
    val isDarkMode by userViewModel.isDarkMode.observeAsState()
    val isSystemDark = isSystemInDarkTheme()
    val effectiveDarkMode = isDarkMode ?: isSystemDark

    ProvideAppColors {
        val colors = LocalAppColors.current

        val lightColors = remember {
            lightColorScheme(
                primary = colors.primary,
                onPrimary = colors.text,
                secondary = colors.accent,
                onSecondary = colors.text,
                background = colors.background,
                onBackground = colors.text,
                surface = colors.surface,
                onSurface = colors.text,
                error = colors.error,
                onError = colors.textDark,
                primaryContainer = colors.primary,
                onPrimaryContainer = colors.text,
                secondaryContainer = colors.accent,
                onSecondaryContainer = colors.text,
                surfaceVariant = colors.surface,
                outline = colors.text,
            )
        }

        val darkColors = remember {
            darkColorScheme(
                primary = colors.primaryDark,
                onPrimary = colors.textDark,
                secondary = colors.accentDark,
                onSecondary = colors.textDark,
                background = colors.backgroundDark,
                onBackground = colors.textDark,
                surface = colors.surfaceDark,
                onSurface = colors.textDark,
                error = colors.errorDark,
                onError = colors.text,
                primaryContainer = colors.primaryDark,
                onPrimaryContainer = colors.textDark,
                secondaryContainer = colors.accentDark,
                onSecondaryContainer = colors.textDark,
                surfaceVariant = colors.surfaceDark,
                outline = colors.textDark
            )
        }

        MaterialTheme(
            colorScheme = if (effectiveDarkMode) darkColors else lightColors,
            typography = Typography,
            content = content
        )
    }
}