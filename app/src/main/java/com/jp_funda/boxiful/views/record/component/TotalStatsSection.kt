package com.jp_funda.boxiful.views.record.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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
                labelString = stringResource(id = R.string.record_number_of_trainings),
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

/** Row for show one stats field. */
@Composable
fun StatsRow(
    modifier: Modifier = Modifier,
    vectorIcon: ImageVector? = null,
    painterIcon: Painter? = null,
    iconColor: Color,
    labelString: String,
    valueString: String,
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Spacer(modifier = Modifier.width(5.dp))
        vectorIcon?.let {
            Icon(
                imageVector = it,
                contentDescription = stringResource(id = R.string.desc_icon),
                tint = iconColor,
                modifier = Modifier.padding(vertical = 2.dp)
            )
        }
        painterIcon?.let {
            Icon(
                painter = it,
                contentDescription = stringResource(id = R.string.desc_icon),
                tint = iconColor,
                modifier = Modifier.padding(vertical = 2.dp)
            )
        }
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = labelString,
            color = Color.DarkGray,
            fontWeight = FontWeight.ExtraBold,
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(text = valueString)
        Spacer(modifier = Modifier.width(5.dp))
    }
}