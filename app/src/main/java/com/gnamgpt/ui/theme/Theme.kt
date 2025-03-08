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
import com.gnamgpt.ui.theme.AppColors
import com.gnamgpt.ui.theme.LocalAppColors
import com.gnamgpt.ui.theme.ProvideAppColors

// Applica il tema personalizzato all'app
@Composable
fun GnamGPTTheme(
    isDarkMode: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    ProvideAppColors {
        val colors = LocalAppColors.current

        val lightColors = remember {
            lightColorScheme(
                primary = colors.primary400,
                onPrimary = colors.text100,
                secondary = colors.accent400,
                onSecondary = colors.text50,
                tertiary = colors.secondary400,
                onTertiary = colors.text100,
                background = colors.background200,
                onBackground = colors.text100,
                surface = colors.background200,
                onSurface = colors.text100,
                surfaceVariant = colors.background300,
                error = colors.accent400,
                onError = colors.text950,
                primaryContainer = colors.primary200,
                onPrimaryContainer = colors.text100,
                secondaryContainer = colors.accent200,
                onSecondaryContainer = colors.text100,
                tertiaryContainer = colors.secondary200,
                outline = colors.text300
            )
        }

        val darkColors = remember {
            darkColorScheme(
                primary = colors.primary400,
                onPrimary = colors.text900,
                secondary = colors.accent400,
                onSecondary = colors.text900,
                tertiary = colors.secondary600,
                onTertiary = colors.text900,
                background = colors.background200,
                onBackground = colors.text900,
                surface = colors.background900,
                onSurface = colors.text900,
                surfaceVariant = colors.background700,
                error = colors.accent400,
                onError = colors.text950,
                primaryContainer = colors.primary700,
                onPrimaryContainer = colors.text800,
                secondaryContainer = colors.accent700,
                onSecondaryContainer = colors.text800,
                tertiaryContainer = colors.secondary700,
                outline = colors.text600
            )
        }

        MaterialTheme(
            colorScheme = if (isDarkMode) darkColors else lightColors,
            typography = Typography,
            content = content
        )
    }
}