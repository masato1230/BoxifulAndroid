package com.jp_funda.boxiful.views.home.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.models.NetworkStatus
import com.jp_funda.boxiful.ui.theme.Green500
import com.jp_funda.boxiful.ui.theme.Red500
import com.jp_funda.boxiful.ui.theme.Yellow500
import com.jp_funda.boxiful.views.components.SnsShareButtons
import com.jp_funda.boxiful.views.home.HomeViewModel
import com.jp_funda.boxiful.views.record.component.StatsRow

@Composable
fun TodayStatsSection(modifier: Modifier = Modifier) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val networkStatus = viewModel.networkStatus.observeAsState()

    viewModel.getTrainingResults()

    if (networkStatus.value == NetworkStatus.Success) {
        Column(modifier = modifier) {
            // Title
            Text(
                text = stringResource(id = R.string.home_today_stats_title),
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
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
                        labelString = stringResource(id = R.string.home_number_of_trainings),
                        valueString = stringResource(
                            id = R.string.unit_times,
                            viewModel.todayNumberOfTrainings
                        ),
                    )
                    // Boxiful points
                    StatsRow(
                        vectorIcon = Icons.Default.CheckCircle,
                        iconColor = Yellow500,
                        labelString = stringResource(id = R.string.home_boxiful_point),
                        valueString = stringResource(
                            id = R.string.unit_points,
                            viewModel.todayBoxifulPoints,
                        ),
                    )
                    // Calorie consumption
                    StatsRow(
                        painterIcon = painterResource(id = R.drawable.ic_fire),
                        iconColor = Red500,
                        labelString = stringResource(id = R.string.home_calorie_consumption),
                        valueString = stringResource(
                            id = R.string.unit_kcal,
                            viewModel.todayCalorieConsumption,
                        ),
                    )
                }
            }
            Spacer(modifier = Modifier.height(5.dp))

            // Share buttons
            SnsShareButtons(
                shareMessage = stringResource(
                    id = R.string.home_today_share_template,
                    viewModel.todayCalorieConsumption,
                    viewModel.todayNumberOfTrainings,
                )
            )
        }
    }
}