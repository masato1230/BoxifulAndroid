package com.jp_funda.boxiful.views.components.calendar_heat_map

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.jp_funda.boxiful.utils.date.DateIterator
import java.time.LocalDate

@Composable
fun CalendarHeatMap(
    startDate: LocalDate,
    endDate: LocalDate = LocalDate.now()
) {
    val dates = DateIterator(startDate, endDate, 1)

    Column() {
        dates.forEach { date ->
            Text(text = date.toString())
        }
    }
}