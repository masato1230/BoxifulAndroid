package com.jp_funda.boxiful.views.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AnimatedPieChart(
    modifier: Modifier = Modifier,
    indicatorValue: Float,
    maxIndicatorValue: Float = 100f,
    indicatorColor: Color = MaterialTheme.colors.primary,
    backgroundIndicator: Color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
    indicatorStrokeWidth: Float = 50f,
    animationDelay: Long = 500,
    animationDuration: Int = 1000,
) {
    val animatedIndicatorValue = remember { Animatable(0f) }
    val composableScope = rememberCoroutineScope()
    LaunchedEffect(key1 = indicatorValue) {
        animatedIndicatorValue.animateTo(0f)
        composableScope.launch {
            delay(animationDelay)
            animatedIndicatorValue.animateTo(indicatorValue)
        }
    }
    val animatedSweepAngle by animateFloatAsState(
        targetValue = 360f * animatedIndicatorValue.value / maxIndicatorValue,
        animationSpec = tween(animationDuration),
    )

    Canvas(modifier = modifier.aspectRatio(1f)) {
        // Indicator background
        drawArc(
            size = Size(size.width - indicatorStrokeWidth, size.height - indicatorStrokeWidth),
            topLeft = Offset(indicatorStrokeWidth / 2, indicatorStrokeWidth / 2),
            color = backgroundIndicator,
            startAngle = 0f,
            sweepAngle = 360f,
            useCenter = false,
            style = Stroke(
                width = indicatorStrokeWidth,
                cap = StrokeCap.Round,
            )
        )
        // Indicator foreground
        drawArc(
            size = Size(size.width - indicatorStrokeWidth, size.height - indicatorStrokeWidth),
            topLeft = Offset(indicatorStrokeWidth / 2, indicatorStrokeWidth / 2),
            color = indicatorColor,
            startAngle = -90f,
            sweepAngle = animatedSweepAngle,
            useCenter = false,
            style = Stroke(
                width = indicatorStrokeWidth,
                cap = StrokeCap.Round,
            )
        )
    }
}

@Preview
@Composable
fun AnimatedPieChartPreview() {
    AnimatedPieChart(indicatorValue = 50f)
}