package com.jp_funda.boxiful.views.components.animated_bar_chart

import android.graphics.Paint
import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.lang.Float.min

@Composable
fun AnimatedBarChart(
    modifier: Modifier = Modifier,
    title: String? = null,
    labelValueDescList: List<Triple<String, Float, String>>,
    indicatorColor: Color = MaterialTheme.colors.primary,
    isEnableRuledLines: Boolean = true,
    ruledLineStep: Int = 50,
    indicatorWidth: Dp = 10.dp,
    columnPadding: Dp = 10.dp,
    maxIndicatorHeight: Dp = 150.dp,
) {
    val columnWeight = 1f / labelValueDescList.size
    var isAnimationStarted by remember { mutableStateOf(false) }
    val animatedIndicatorHeightPercentage by animateFloatAsState(
        targetValue = if (isAnimationStarted) 1f else 0f,
        animationSpec = tween(1000)
    )
    val maxValue = labelValueDescList.maxOf { it.second }
    val indicatorScale = min(1f, maxIndicatorHeight.value / maxValue)
    val context = LocalContext.current

    LaunchedEffect(isAnimationStarted) {
        isAnimationStarted = true
    }

    Column(modifier = modifier) {
        // Title
        title?.let {
            Text(text = it, style = MaterialTheme.typography.caption)
        }
        // Graphic Content
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = maxValue.dp * indicatorScale)
                .drawBehind {
                    // Draw ruled lines
                    if (isEnableRuledLines) {
                        var ruledLineValue = 0
                        val textPaint = Paint()
                        textPaint.apply {
                            textSize = 30f
                            color = android.graphics.Color.WHITE
                        }
                        while (ruledLineValue < maxValue) {
                            drawLine(
                                start = Offset(
                                    x = 0f,
                                    y = size.height - ruledLineValue * density * indicatorScale,
                                ),
                                end = Offset(
                                    x = size.width,
                                    y = size.height - ruledLineValue * density * indicatorScale,
                                ),
                                color = Color.Gray,
                            )
                            drawContext.canvas.nativeCanvas.drawText(
                                ruledLineValue.toString(),
                                size.width - textPaint.textSize,
                                size.height - ruledLineValue * density * indicatorScale,
                                textPaint,
                            )
                            ruledLineValue += ruledLineStep
                        }
                    }
                },
            verticalAlignment = Alignment.Bottom,
        ) {
            for (triple in labelValueDescList) {
                val value = triple.second
                Box(
                    modifier = Modifier
                        .weight(columnWeight)
                        .padding(horizontal = columnPadding)
                        .clickable {
                            Toast
                                .makeText(
                                    context,
                                    triple.third,
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                        },
                    contentAlignment = Alignment.BottomCenter,
                ) {
                    Box(
                        modifier = Modifier
                            .height(value.dp * animatedIndicatorHeightPercentage * indicatorScale)
                            .widthIn(min = 1.dp)
                            .clip(RoundedCornerShape(1000.dp))
                            .width(indicatorWidth)
                            .background(indicatorColor)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        // Labels
        Row(modifier = Modifier.fillMaxWidth()) {
            for (triple in labelValueDescList) {
                val label = triple.first
                Text(
                    text = label,
                    modifier = Modifier.weight(columnWeight),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}