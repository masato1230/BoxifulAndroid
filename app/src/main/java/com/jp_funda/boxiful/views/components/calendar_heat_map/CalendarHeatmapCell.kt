package com.jp_funda.boxiful.views.components.calendar_heat_map

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import java.time.LocalDate

@Composable
fun CalendarHeatmapCell(
    date: LocalDate,
    cellSize: DpSize,
    cellPadding: Dp,
    roundSize: Dp,
    level: CalendarHeatMapLevel,
    alpha: Float = 0.5f,
) {
    Box(
        modifier = Modifier
            .padding(cellPadding)
            .size(cellSize)
            .clip(RoundedCornerShape(roundSize))
            .background(level.color.copy(alpha = minOf(1f, alpha + 0.3f)))
            .border(width = 1.dp, color = level.color, shape = RoundedCornerShape(roundSize))
    )
}