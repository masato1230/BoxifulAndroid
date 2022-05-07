package com.jp_funda.boxiful.views.components.animated_bar_chart

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedBarChart(
    modifier: Modifier = Modifier,
    labelValueMap: Map<String, Int>,
    indicatorColor: Color = MaterialTheme.colors.primary,
) {
    val columnWeight = 1f / labelValueMap.size
    var isAnimationStarted by remember { mutableStateOf(false) }
    val animatedIndicatorHeightPercentage by animateFloatAsState(
        targetValue = if (isAnimationStarted) 1f else 0f,
        animationSpec = tween(2000)
    )

    LaunchedEffect(isAnimationStarted) {
        isAnimationStarted = true
    }

    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth()) {
            for (value in labelValueMap.values) {
                Box(
                    modifier = Modifier
                        .height(value.dp * animatedIndicatorHeightPercentage)
                        .clip(RoundedCornerShape(1000.dp))
                        .weight(columnWeight)
                        .background(indicatorColor)
                )
            }
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            for (label in labelValueMap.keys) {
                Text(text = label, modifier = Modifier.weight(columnWeight))
            }
        }
    }
}