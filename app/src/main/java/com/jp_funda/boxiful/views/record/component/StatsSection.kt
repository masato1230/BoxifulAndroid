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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.ui.theme.Green500
import com.jp_funda.boxiful.ui.theme.Yellow500
import com.jp_funda.boxiful.views.record.RecordViewModel

@Composable
fun StatsSection() {
    val viewModel = hiltViewModel<RecordViewModel>()

    Card(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = Color.White,
        contentColor = Color.Black,
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            // Number of trainings
            StatsRow(
                icon = Icons.Default.DateRange,
                iconColor = Green500,
                labelString = stringResource(id = R.string.record_number_of_trainings),
                valueString = stringResource(id = R.string.unit_times, viewModel.numberOfTrainings),
            )
            // Boxiful points
            StatsRow(
                icon = Icons.Default.CheckCircle,
                iconColor = Yellow500,
                labelString = stringResource(id = R.string.record_total_boxiful_point),
                valueString = stringResource(
                    id = R.string.unit_points,
                    viewModel.totalBoxifulPoints,
                ),
            )
        }
    }
}

/** Row for show one stats field. */
@Composable
fun StatsRow(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    iconColor: Color,
    labelString: String,
    valueString: String,
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = stringResource(id = R.string.desc_icon),
            tint = iconColor,
            modifier = Modifier.padding(vertical = 2.dp)
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = labelString,
            color = Color.DarkGray,
            fontWeight = FontWeight.ExtraBold,
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(text = valueString)
    }
}