package com.jp_funda.boxiful.views.record.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.ui.theme.Green500
import com.jp_funda.boxiful.ui.theme.Red500
import com.jp_funda.boxiful.ui.theme.Yellow500
import com.jp_funda.boxiful.views.components.animated_bar_chart.AnimatedBarChart
import com.jp_funda.boxiful.views.record.RecordViewModel
import java.time.format.TextStyle
import java.util.*

@Composable
fun WeeklyStatsSection() {
    val viewModel = hiltViewModel<RecordViewModel>()

    Column {
        // Title
        Text(
            text = stringResource(id = R.string.record_weekly_title),
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.ExtraBold,
        )
        Spacer(modifier = Modifier.height(10.dp))

        // Stats Card
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
                    labelString = stringResource(id = R.string.record_weekly_number_of_trainings),
                    valueString = stringResource(
                        id = R.string.unit_times,
                        viewModel.weeklyNumberOfTrainings
                    ),
                )
                // Boxiful points
                StatsRow(
                    vectorIcon = Icons.Default.CheckCircle,
                    iconColor = Yellow500,
                    labelString = stringResource(id = R.string.record_weekly_boxiful_point),
                    valueString = stringResource(
                        id = R.string.unit_points,
                        viewModel.weeklyBoxifulPoints,
                    ),
                )
                // Calorie consumption
                StatsRow(
                    painterIcon = painterResource(id = R.drawable.ic_fire),
                    iconColor = Red500,
                    labelString = stringResource(id = R.string.record_weekly_calorie_consumption),
                    valueString = stringResource(
                        id = R.string.unit_kcal,
                        viewModel.weeklyCalorieConsumption,
                    ),
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))

        // Bar chart
        AnimatedBarChart(
            labelValueMap = viewModel.dateCaloriePairsInWeek.map {
                it.first.dayOfWeek.getDisplayName(
                    TextStyle.SHORT,
                    Locale.getDefault(),
                ) to it.second.toFloat()
            },
            indicatorColor = Red500,
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .background(Color.DarkGray.copy(alpha = 0.3f))
                .padding(5.dp)
        )
    }
}