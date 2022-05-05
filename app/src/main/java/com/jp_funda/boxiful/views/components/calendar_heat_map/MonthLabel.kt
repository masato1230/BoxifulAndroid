package com.jp_funda.boxiful.views.components.calendar_heat_map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

@Composable
fun MonthLabel(
    columnIndexesOfFirstDateInMonth: Map<LocalDate, Int>,
    cellSize: DpSize,
    cellPadding: Dp,
    height: Dp,
    textStyle: TextStyle,
    locale: Locale,
) {
    Box(modifier = Modifier.height(height)) {
        var iterateIndex = 0
        columnIndexesOfFirstDateInMonth.forEach { (date, index) ->
            iterateIndex++
            Text(
                text = date.month.getDisplayName(textStyle, locale),
                modifier = Modifier
                    .offset(
                        x = (cellSize.width + cellPadding * 2) * index + cellPadding * iterateIndex,
                        y = 0.dp,
                    ),
                color = Color.LightGray,
            )
        }
    }
}