package com.jp_funda.boxiful.views.result

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.jp_funda.boxiful.models.SingleMenuScores

@Composable
fun ResultScreen(singleMenuScores: SingleMenuScores) {
    Text(text = singleMenuScores.singleMenu.name)
}