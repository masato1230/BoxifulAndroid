package com.jp_funda.boxiful.views.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp

@Composable
fun StackedColumnChart(
    modifier: Modifier = Modifier,
    valueColorSets: List<ValueColorSet>,
    maxValue: Int,
    backgroundColor: Color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
    strokeWidth: Float = 50f,
) {
    Canvas(modifier = modifier
        .heightIn(30.dp)
        .widthIn(100.dp)) {
        // Indicator background
        drawLine(
            color = backgroundColor,
            start = Offset(strokeWidth / 2, size.height / 2),
            end = Offset(size.width - strokeWidth /2, size.height / 2),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )

        var stackedValue = 0
        valueColorSets.reversed().forEach { valueColorSet ->
            val percentage = 1 - stackedValue.toFloat() / maxValue
            drawLine(
                color = valueColorSet.color,
                start = Offset(strokeWidth / 2, size.height / 2),
                end = Offset(size.width * percentage - strokeWidth / 2, size.height / 2),
                strokeWidth = strokeWidth,
                cap = StrokeCap.Round,
            )
            stackedValue += valueColorSet.value
        }
    }
}

data class ValueColorSet(
    val value: Int,
    val color: Color,
)