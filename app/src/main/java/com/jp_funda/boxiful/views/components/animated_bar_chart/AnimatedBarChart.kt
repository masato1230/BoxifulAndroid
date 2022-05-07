package com.jp_funda.boxiful.views.components.animated_bar_chart

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedBarChart(
    modifier: Modifier = Modifier,
    labelValueMap: List<Pair<String, Int>>,
    indicatorColor: Color = MaterialTheme.colors.primary,
    indicatorWidth: Dp = 10.dp,
    columnPadding: Dp = 10.dp,
) {
    val columnWeight = 1f / labelValueMap.size
    var isAnimationStarted by remember { mutableStateOf(false) }
    val animatedIndicatorHeightPercentage by animateFloatAsState(
        targetValue = if (isAnimationStarted) 1f else 0f,
        animationSpec = tween(2000)
    )
    val maxValue = labelValueMap.maxOf { it.second }

    LaunchedEffect(isAnimationStarted) {
        isAnimationStarted = true
    }

    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = maxValue.dp),
            verticalAlignment = Alignment.Bottom,
        ) {
            for (pair in labelValueMap) {
                val value = pair.second
                Box(
                    modifier = Modifier
                        .weight(columnWeight)
                        .padding(horizontal = columnPadding),
                    contentAlignment = Alignment.BottomCenter,
                ) {
                    Box(
                        modifier = Modifier
                            .height(value.dp * animatedIndicatorHeightPercentage)
                            .widthIn(min = 1.dp)
                            .clip(RoundedCornerShape(1000.dp))
                            .width(indicatorWidth)
                            .background(indicatorColor)
                    )
                }
            }
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            for (pair in labelValueMap) {
                val label = pair.first
                Text(
                    text = label,
                    modifier = Modifier.weight(columnWeight),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}