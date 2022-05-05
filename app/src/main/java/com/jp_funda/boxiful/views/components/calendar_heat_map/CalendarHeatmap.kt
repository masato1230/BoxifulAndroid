package com.jp_funda.boxiful.views.components.calendar_heat_map

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.jp_funda.boxiful.ui.theme.Green500
import com.jp_funda.boxiful.utils.date.DateIterator
import java.time.DayOfWeek
import java.time.LocalDate

@Composable
fun CalendarHeatmap(
    startDate: LocalDate,
    endDate: LocalDate = LocalDate.now(),
    cellSize: DpSize,
    cellPadding: Dp,
    roundSize: Dp,
) {
    val dates = DateIterator(startDate, endDate, 1)
    val datesSeparatedByDate = mutableListOf<List<LocalDate>>()

    var datesInOneWeek = mutableListOf<LocalDate>()
    dates.forEach { date ->
        datesInOneWeek.add(date)
        if (date.dayOfWeek == DayOfWeek.SATURDAY || date == endDate) {
            datesSeparatedByDate.add(datesInOneWeek)
            datesInOneWeek = mutableListOf()
        }
    }

    LazyRow() {
        item {
            WeekDayLabel(cellSize = cellSize, cellPadding = cellPadding)
        }
        items(datesSeparatedByDate) { datesInOneColumn ->
            Column() {
                datesInOneColumn.forEach { date ->
                    if (date == startDate && date.dayOfWeek != DayOfWeek.SUNDAY) {
                        val offsetCount = 1 + date.dayOfWeek.ordinal
                        for (i in 1..offsetCount) {
                            // TODO use TransparentColor
                            CalendarHeatmapCell(cellSize, cellPadding, roundSize)
                        }
                    }
                    CalendarHeatmapCell(cellSize, cellPadding, roundSize)
                }
            }
        }
    }
}

@Composable
fun CalendarHeatmapCell(
    cellSize: DpSize,
    cellPadding: Dp,
    roundSize: Dp,
) {
    Box(
        modifier = Modifier
            .padding(cellPadding)
            .size(cellSize)
            .clip(RoundedCornerShape(roundSize))
            .background(Green500)
    )
}

@Composable
fun WeekDayLabel(
    cellSize: DpSize,
    cellPadding: Dp,
) {
    val cellHeightIncludePadding = cellSize.height + cellPadding * 2

    Column(modifier = Modifier.padding(end = 5.dp)) {
        // Offset for text top aligned
        Spacer(modifier = Modifier.height(cellPadding))
        Spacer(modifier = Modifier.height(cellHeightIncludePadding))
        Text(
            text = "Mon",
            modifier = Modifier.height(cellHeightIncludePadding),
        )
        Spacer(modifier = Modifier.height(cellHeightIncludePadding))
        Text(
            text = "Wed",
            modifier = Modifier.height(cellHeightIncludePadding),
        )
        Spacer(modifier = Modifier.height(cellHeightIncludePadding))
        Text(
            text = "Fri",
            modifier = Modifier.height(cellHeightIncludePadding),
        )
    }
}