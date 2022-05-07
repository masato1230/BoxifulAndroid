package com.jp_funda.boxiful.views.record.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.ui.theme.Green500
import com.jp_funda.boxiful.ui.theme.Red500
import com.jp_funda.boxiful.ui.theme.Yellow500
import com.jp_funda.boxiful.views.record.RecordViewModel

@Composable
fun TotalStatsSection() {
    val viewModel = hiltViewModel<RecordViewModel>()

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
                valueString = stringResource(id = R.string.unit_times, viewModel.numberOfTrainings),
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
}