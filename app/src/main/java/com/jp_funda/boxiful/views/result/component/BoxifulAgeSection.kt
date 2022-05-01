package com.jp_funda.boxiful.views.result.component

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun BoxifulAgeSection() {
    Text(text = "あなたのボクシフル年齢は", color = Color.White, style = MaterialTheme.typography.h6)
}