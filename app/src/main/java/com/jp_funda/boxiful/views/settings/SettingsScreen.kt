package com.jp_funda.boxiful.views.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jp_funda.boxiful.views.components.header.Header
import com.jp_funda.boxiful.views.settings.component.CopyrightSection

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingsScreen(navController: NavController) {
    Scaffold(topBar = { Header(navController) }) {
        SettingsMainContent(navController = navController)
    }
}

@Composable
fun SettingsMainContent(navController: NavController) {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        CopyrightSection()
    }
}