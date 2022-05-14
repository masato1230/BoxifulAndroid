package com.jp_funda.boxiful.views.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/**
 * Animated Pie chart with title and center label.
 */
@Composable
fun LabeledPieChart(
    indicatorValue: Float,
    title: String,
    centerLabel: String,
    modifier: Modifier = Modifier,
    titleColor: Color = Color.White,
    centerLabelColor: Color = Color.White,
    indicatorColor: Color = MaterialTheme.colors.primary,
) {
    Column(modifier = modifier.padding(10.dp)) {
        Text(
            text = title,
            color = titleColor,
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.ExtraBold,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            AnimatedPieChart(
                indicatorValue = indicatorValue,
                indicatorColor = indicatorColor,
                backgroundIndicator = Color.Transparent,
            )
            Text(text = centerLabel, color = centerLabelColor, style = MaterialTheme.typography.h4)
        }
    }
}