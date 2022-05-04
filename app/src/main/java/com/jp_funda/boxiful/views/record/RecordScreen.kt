package com.jp_funda.boxiful.views.record

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.jp_funda.boxiful.views.components.header.Header

@Composable
fun RecordScreen(navController: NavController) {
    Scaffold(topBar = { Header(navController) }) {
        RecordMainContent(modifier = Modifier.padding(it), navController = navController)
    }
}

@Composable
fun RecordMainContent(modifier: Modifier = Modifier, navController: NavController) {
    Text(text = "Record")
}