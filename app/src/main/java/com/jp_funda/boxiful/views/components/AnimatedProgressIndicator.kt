package com.jp_funda.boxiful.views.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp

/**
 * Animated Progress Indicator.
 * @param progress progress percentage value in range of 0 to 1
 */
@Composable
fun AnimatedProgressIndicator(
    modifier: Modifier = Modifier,
    progress: Float,
    indicatorStrokeWidth: Float = 50f,
    indicatorColor: Color = MaterialTheme.colors.primary,
    backgroundIndicatorColor: Color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
) {
    val animatedIndicatorValue = remember { Animatable(0f) }
    LaunchedEffect(key1 = progress) {
        animatedIndicatorValue.animateTo(progress)
    }
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(1000),
    )

    Canvas(
        modifier = modifier
            .heightIn(min = 30.dp)
            .widthIn(100.dp)
    ) {
        // Indicator background
        drawLine(
            color = backgroundIndicatorColor,
            start = Offset(indicatorStrokeWidth / 2, size.height / 2),
            end = Offset(size.width - indicatorStrokeWidth / 2, size.height / 2),
            strokeWidth = indicatorStrokeWidth,
            cap = StrokeCap.Round,
        )
        // Indicator foreground
        drawLine(
            color = indicatorColor,
            start = Offset(indicatorStrokeWidth / 2, size.height / 2),
            end = Offset(
                x = (animatedProgress * (size.width - indicatorStrokeWidth) + indicatorStrokeWidth / 2),
                y = size.height / 2,
            ),
            strokeWidth = indicatorStrokeWidth,
            cap = StrokeCap.Round,
        )
    }
}