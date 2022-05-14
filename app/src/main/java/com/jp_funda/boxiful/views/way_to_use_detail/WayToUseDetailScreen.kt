package com.jp_funda.boxiful.views.way_to_use_detail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.jp_funda.boxiful.ui.theme.Background
import com.jp_funda.boxiful.views.components.header.Header
import com.jp_funda.boxiful.views.way_to_use.WayToUsePage

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WayToUseDetailScreen(navController: NavController, page: WayToUsePage) {
    Scaffold(topBar = { Header(navController) }) {
        Background()
        WayToUseDetailMainContent(navController, page)
    }
}

@Composable
fun WayToUseDetailMainContent(navController: NavController, page: WayToUsePage) {
    Column {
        Text(text = page.name)
    }
}