package com.jp_funda.boxiful.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

@Composable
fun PagesBackground() {
    val shape = GenericShape { size, _ ->
        lineTo(size.width, 0f)
        lineTo(-size.width, size.height)
        lineTo(0f, -size.height)
    }
    Box(modifier = Modifier.fillMaxSize().background(Yellow500))
    Box(modifier = Modifier
        .fillMaxSize()
        .clip(shape)
        .background(Pink400))
}