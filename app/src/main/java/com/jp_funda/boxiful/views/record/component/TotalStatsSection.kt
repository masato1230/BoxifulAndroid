package com.jp_funda.boxiful.views.record.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.ui.theme.Green500
import com.jp_funda.boxiful.ui.theme.Red500
import com.jp_funda.boxiful.ui.theme.Yellow500
import com.jp_funda.boxiful.views.components.calendar_heat_map.CalendarHeatmap
import com.jp_funda.boxiful.views.record.RecordViewModel

@Composable
fun TotalStatsSection() {
    val viewModel = hiltViewModel<RecordViewModel>()

    Column {
        // Title
        Text(
            text = stringResource(id = R.string.record_total_title),
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(top = 10.dp),
        )
        Spacer(modifier = Modifier.height(10.dp))

        // Stats card
        Card(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.White,
            contentColor = Color.Black,
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                // Number of trainings
                StatsRow(
                    vectorIcon = Icons.Default.DateRange,
                    iconColor = Green500,
                    labelString = stringResource(id = R.string.record_total_number_of_trainings),
                    valueString = stringResource(
                        id = R.string.unit_times,
                        viewModel.totalNumberOfTrainings
                    ),
                )
                // Boxiful points
                StatsRow(
                    vectorIcon = Icons.Default.CheckCircle,
                    iconColor = Yellow500,
                    labelString = stringResource(id = R.string.record_total_boxiful_point),
                    valueString = stringResource(
                        id = R.string.unit_points,
                        viewModel.totalBoxifulPoints,
                    ),
                )
                // Calorie consumption
                StatsRow(
                    painterIcon = painterResource(id = R.drawable.ic_fire),
                    iconColor = Red500,
                    labelString = stringResource(id = R.string.record_total_calorie_consumption),
                    valueString = stringResource(
                        id = R.string.unit_kcal,
                        viewModel.totalCalorieConsumption,
                    ),
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))

        // Heatmap
        CalendarHeatmap(
            modifier = Modifier.padding(10.dp),
            startDate = viewModel.resultStartDate,
            cellSize = DpSize(20.dp, 20.dp),
            cellPadding = 2.dp,
            roundSize = 5.dp,
            cellLevelMap = viewModel.dateTrainingLevelMap,
            cellPopupTextsMap = viewModel.dateTextsMap,
        )
    }
}