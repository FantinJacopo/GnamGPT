package com.gnamgpt.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.gnamgpt.R

data class AppColors(
    val background50: Color,
    val background100: Color,
    val background200: Color,
    val background300: Color,
    val background400: Color,
    val background500: Color,
    val background600: Color,
    val background700: Color,
    val background800: Color,
    val background900: Color,
    val background950: Color,
    val text50: Color,
    val text100: Color,
    val text200: Color,
    val text300: Color,
    val text400: Color,
    val text500: Color,
    val text600: Color,
    val text700: Color,
    val text800: Color,
    val text900: Color,
    val text950: Color,
    val primary50: Color,
    val primary100: Color,
    val primary200: Color,
    val primary300: Color,
    val primary400: Color,
    val primary500: Color,
    val primary600: Color,
    val primary700: Color,
    val primary800: Color,
    val primary900: Color,
    val primary950: Color,
    val secondary50: Color,
    val secondary100: Color,
    val secondary200: Color,
    val secondary300: Color,
    val secondary400: Color,
    val secondary500: Color,
    val secondary600: Color,
    val secondary700: Color,
    val secondary800: Color,
    val secondary900: Color,
    val secondary950: Color,
    val accent50: Color,
    val accent100: Color,
    val accent200: Color,
    val accent300: Color,
    val accent400: Color,
    val accent500: Color,
    val accent600: Color,
    val accent700: Color,
    val accent800: Color,
    val accent900: Color,
    val accent950: Color,
)

val LocalAppColors = staticCompositionLocalOf<AppColors> {
    error("No AppColors provided")
}

@Composable
fun ProvideAppColors(content: @Composable () -> Unit) {
    val colors = AppColors(
        background50 = colorResource(id = R.color.Background50),
        background100 = colorResource(id = R.color.Background100),
        background200 = colorResource(id = R.color.Background200),
        background300 = colorResource(id = R.color.Background300),
        background400 = colorResource(id = R.color.Background400),
        background500 = colorResource(id = R.color.Background500),
        background600 = colorResource(id = R.color.Background600),
        background700 = colorResource(id = R.color.Background700),
        background800 = colorResource(id = R.color.Background800),
        background900 = colorResource(id = R.color.Background900),
        background950 = colorResource(id = R.color.Background950),

        text50 = colorResource(id = R.color.Text50),
        text100 = colorResource(id = R.color.Text100),
        text200 = colorResource(id = R.color.Text200),
        text300 = colorResource(id = R.color.Text300),
        text400 = colorResource(id = R.color.Text400),
        text500 = colorResource(id = R.color.Text500),
        text600 = colorResource(id = R.color.Text600),
        text700 = colorResource(id = R.color.Text700),
        text800 = colorResource(id = R.color.Text800),
        text900 = colorResource(id = R.color.Text900),
        text950 = colorResource(id = R.color.Text950),

        primary50 = colorResource(id = R.color.Primary50),
        primary100 = colorResource(id = R.color.Primary100),
        primary200 = colorResource(id = R.color.Primary200),
        primary300 = colorResource(id = R.color.Primary300),
        primary400 = colorResource(id = R.color.Primary400),
        primary500 = colorResource(id = R.color.Primary500),
        primary600 = colorResource(id = R.color.Primary600),
        primary700 = colorResource(id = R.color.Primary700),
        primary800 = colorResource(id = R.color.Primary800),
        primary900 = colorResource(id = R.color.Primary900),
        primary950 = colorResource(id = R.color.Primary950),

        secondary50 = colorResource(id = R.color.Secondary50),
        secondary100 = colorResource(id = R.color.Secondary100),
        secondary200 = colorResource(id = R.color.Secondary200),
        secondary300 = colorResource(id = R.color.Secondary300),
        secondary400 = colorResource(id = R.color.Secondary400),
        secondary500 = colorResource(id = R.color.Secondary500),
        secondary600 = colorResource(id = R.color.Secondary600),
        secondary700 = colorResource(id = R.color.Secondary700),
        secondary800 = colorResource(id = R.color.Secondary800),
        secondary900 = colorResource(id = R.color.Secondary900),
        secondary950 = colorResource(id = R.color.Secondary950),

        accent50 = colorResource(id = R.color.Accent50),
        accent100 = colorResource(id = R.color.Accent100),
        accent200 = colorResource(id = R.color.Accent200),
        accent300 = colorResource(id = R.color.Accent300),
        accent400 = colorResource(id = R.color.Accent400),
        accent500 = colorResource(id = R.color.Accent500),
        accent600 = colorResource(id = R.color.Accent600),
        accent700 = colorResource(id = R.color.Accent700),
        accent800 = colorResource(id = R.color.Accent800),
        accent900 = colorResource(id = R.color.Accent900),
        accent950 = colorResource(id = R.color.Accent950)
    )
    CompositionLocalProvider(LocalAppColors provides colors, content = content)
}