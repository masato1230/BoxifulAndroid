package com.jp_funda.boxiful.views.result.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.models.ResultStats
import com.jp_funda.boxiful.views.components.SnsShareButtons

@Composable
fun BoxifulAgeSection(resultStats: ResultStats) {
    Column {
        // Rational
        Text(
            text = stringResource(id = R.string.result_your_boxiful_age_is),
            color = Color.White,
            style = MaterialTheme.typography.h6,
        )
        Spacer(modifier = Modifier.height(20.dp))
        // Age label
        Text(
            text = stringResource(id = R.string.result_age, resultStats.boxifulAge),
            color = Color.White,
            style = MaterialTheme.typography.h3,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.ExtraBold,
        )
        Spacer(modifier = Modifier.height(20.dp))
        // SNS share buttons
        SnsShareButtons(resultStats)
    }
}