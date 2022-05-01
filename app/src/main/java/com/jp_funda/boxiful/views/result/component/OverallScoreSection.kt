package com.jp_funda.boxiful.views.result.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jp_funda.boxiful.ui.theme.Gray900
import com.jp_funda.boxiful.ui.theme.Pink500
import com.jp_funda.boxiful.views.components.AnimatedPieChart

@Composable
fun OverallScoreSection() {
    Column {
        Text(
            text = "トレーニング評価",
            color = Color.White,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(10.dp)
        )
        Box(
            modifier = Modifier.padding(horizontal = 50.dp, vertical = 10.dp),
            contentAlignment = Alignment.Center,
        ) {
            AnimatedPieChart(
                indicatorValue = 88f,
                indicatorColor = Pink500,
                modifier = Modifier.fillMaxSize(),
                indicatorStrokeWidth = 100f,
                backgroundIndicator = Gray900,
            )
            Text(text = "88点", color = Color.LightGray, style = MaterialTheme.typography.h3)
        }
    }
}