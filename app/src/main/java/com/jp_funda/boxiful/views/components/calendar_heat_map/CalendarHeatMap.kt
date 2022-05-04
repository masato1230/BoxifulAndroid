package com.jp_funda.boxiful.views.components.calendar_heat_map

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.jp_funda.boxiful.utils.date.DateIterator
import java.time.DayOfWeek
import java.time.LocalDate

@Composable
fun CalendarHeatMap(
    startDate: LocalDate,
    endDate: LocalDate = LocalDate.now()
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
        items(datesSeparatedByDate) { datesInOneColumn ->
            Column() {
                datesInOneColumn.forEach { date ->
                    Text(text = date.toString())
                }
            }
        }
    }

    Column() {
        dates.forEach { date ->
            Text(text = date.toString())
        }
    }
}