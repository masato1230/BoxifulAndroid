package com.jp_funda.boxful.views.home

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
) {
    Text(text = viewModel.title)
}