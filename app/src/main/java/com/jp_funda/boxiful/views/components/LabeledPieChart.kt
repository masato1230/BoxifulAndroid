package com.jp_funda.boxiful.views.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jp_funda.boxiful.ui.theme.Gray900

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
    centerLabelColor: Color = Color.LightGray,
) {
    Column(modifier = modifier) {
        Text(text = title, color = titleColor)
        Spacer(modifier = Modifier.height(10.dp))
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            AnimatedPieChart(indicatorValue = indicatorValue, backgroundIndicator = Gray900)
            Text(text = centerLabel, color = centerLabelColor, style = MaterialTheme.typography.h4)
        }
    }
}