package com.jp_funda.boxiful.views.components.calendar_heat_map

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.jp_funda.boxiful.utils.date.DateIterator
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

@Composable
fun CalendarHeatmap(
    cellLevelMap: Map<LocalDate, CalendarHeatmapLevel>,
    startDate: LocalDate,
    endDate: LocalDate = LocalDate.now(),
    cellSize: DpSize,
    cellPadding: Dp,
    roundSize: Dp,
    textStyle: TextStyle = TextStyle.SHORT,
    locale: Locale = Locale.getDefault(),
) {
    val dates = DateIterator(startDate, endDate, 1)

    // Create dates list which is separated by week
    val datesSeparatedByDate = mutableListOf<List<LocalDate>>()
    var datesInOneWeek = mutableListOf<LocalDate>()
    dates.forEach { date ->
        datesInOneWeek.add(date)
        if (date.dayOfWeek == DayOfWeek.SATURDAY || date == endDate) {
            datesSeparatedByDate.add(datesInOneWeek)
            datesInOneWeek = mutableListOf()
        }
    }

    // Create map list of column index in heat map and first day of month
    val columnIndexesOfFirstDateInMonth = mutableMapOf<LocalDate, Int>()
    datesSeparatedByDate.forEachIndexed { index, weekDates ->
        weekDates.find { it.dayOfMonth == 1 }?.let { date ->
            columnIndexesOfFirstDateInMonth[date] = index
        }
    }

    // Heatmap Contents
    val monthLabelHeight = 20.dp

    Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
        WeekDayLabel(
            cellSize = cellSize,
            cellPadding = cellPadding,
            initialOffset = monthLabelHeight,
            textStyle = textStyle,
            locale = locale,
        )
        Spacer(modifier = Modifier.width(2.dp))
        Column {
            MonthLabel(
                columnIndexesOfFirstDateInMonth = columnIndexesOfFirstDateInMonth,
                cellSize = cellSize,
                cellPadding = cellPadding,
                height = monthLabelHeight,
                textStyle = textStyle,
                locale = locale,
            )
            Row {
                datesSeparatedByDate.forEach { datesInOneColumn ->
                    Column {
                        datesInOneColumn.forEach { date ->
                            // First week's offset
                            if (date == startDate && date.dayOfWeek != DayOfWeek.SUNDAY) {
                                val offsetCount = 1 + date.dayOfWeek.ordinal
                                for (i in 1..offsetCount) {
                                    Box(
                                        modifier = Modifier
                                            .padding(cellPadding)
                                            .size(cellSize)
                                    )
                                }
                            }

                            val cellLevel = cellLevelMap[date]
                            if (cellLevel != null) {
                                CalendarHeatmapCell(
                                    date = date,
                                    cellSize = cellSize,
                                    cellPadding = cellPadding,
                                    roundSize = roundSize,
                                    level = cellLevel,
                                )
                            } else {
                                CalendarHeatmapCell(
                                    date = date,
                                    cellSize = cellSize,
                                    cellPadding = cellPadding,
                                    roundSize = roundSize,
                                    level = CalendarHeatmapLevel.Level0,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}