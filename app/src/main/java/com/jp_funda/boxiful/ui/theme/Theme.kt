package com.jp_funda.boxiful.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val ColorPalette = darkColors(
    primary = Green500,
    primaryVariant = Yellow500,
    secondary = Teal200,
    surface = Color.Black,
)

@Composable
fun BoxfulTheme(content: @Composable () -> Unit) {
    val colors = ColorPalette

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}