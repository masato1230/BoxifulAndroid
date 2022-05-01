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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jp_funda.boxiful.R

@Composable
fun BoxifulAgeSection(boxifulAge: Int) {
    Column {
        Text(
            text = stringResource(id = R.string.result_your_boxiful_age_is),
            color = Color.White,
            style = MaterialTheme.typography.h6,
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(id = R.string.result_age, boxifulAge),
            color = Color.White,
            style = MaterialTheme.typography.h3,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
        // TODO share buttons
    }
}