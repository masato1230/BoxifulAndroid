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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.ui.theme.Gray900
import com.jp_funda.boxiful.ui.theme.Green500
import com.jp_funda.boxiful.ui.theme.Red500
import com.jp_funda.boxiful.ui.theme.Yellow500
import com.jp_funda.boxiful.views.components.LoadingView
import com.jp_funda.boxiful.views.components.SnsShareButtons
import com.jp_funda.boxiful.views.record.RecordViewModel
import com.jp_funda.github_heatmap.GitHubHeatmap
import kotlinx.coroutines.delay

@Composable
fun TotalStatsSection() {
    val viewModel = hiltViewModel<RecordViewModel>()

    Column {
        // Title
        Text(
            text = stringResource(id = R.string.record_total_title),
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
        Spacer(modifier = Modifier.height(5.dp))

        // Share buttons
        SnsShareButtons(
            shareMessage = stringResource(
                id = R.string.record_total_share_template,
                viewModel.totalCalorieConsumption,
                viewModel.totalNumberOfTrainings,
            )
        )
        Spacer(modifier = Modifier.height(5.dp))

        // Heatmap
        val isHeatmapVisible = remember { mutableStateOf(false) }
        LaunchedEffect(key1 = isHeatmapVisible) {
            delay(1000)
            isHeatmapVisible.value = true
        }

        if (isHeatmapVisible.value) {
            GitHubHeatmap(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .background(Gray900),
                startDate = viewModel.resultStartDate,
                cellLevelMap = viewModel.dateTrainingLevelMap,
                cellPopupTextsMap = viewModel.dateTextsMap,
            )
        } else {
            LoadingView(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(RoundedCornerShape(5.dp))
                .background(Gray900)
            )
        }
    }
}