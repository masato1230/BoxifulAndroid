package com.jp_funda.boxiful.views.components.calendar_heat_map

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.*

@Composable
fun WeekDayLabel(
    cellSize: DpSize,
    cellPadding: Dp,
    initialOffset: Dp,
    textStyle: TextStyle,
    locale: Locale,
) {
    val cellHeightIncludePadding = cellSize.height + cellPadding * 2

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // Offset for text top aligned
        Spacer(modifier = Modifier.height(cellPadding * 2 + initialOffset))
        Spacer(modifier = Modifier.height(cellHeightIncludePadding))
        Text(
            text = DayOfWeek.MONDAY.getDisplayName(textStyle, locale),
            modifier = Modifier.height(cellHeightIncludePadding),
        )
        Spacer(modifier = Modifier.height(cellHeightIncludePadding))
        Text(
            text = DayOfWeek.WEDNESDAY.getDisplayName(textStyle, locale),
            modifier = Modifier.height(cellHeightIncludePadding),
        )
        Spacer(modifier = Modifier.height(cellHeightIncludePadding))
        Text(
            text = DayOfWeek.FRIDAY.getDisplayName(textStyle, locale),
            modifier = Modifier.height(cellHeightIncludePadding),
        )
    }
}