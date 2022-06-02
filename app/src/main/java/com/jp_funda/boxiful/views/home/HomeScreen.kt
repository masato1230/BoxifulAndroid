package com.jp_funda.boxiful.views.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jp_funda.boxiful.ui.theme.Background
import com.jp_funda.boxiful.views.components.header.Header
import com.jp_funda.boxiful.views.home.component.SingleMenusList
import com.jp_funda.boxiful.views.home.component.TodayStatsSection
import com.jp_funda.boxiful.views.home.component.TopSection

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(topBar = { Header(navController) }) {
        Background()
        HomeMainContent(modifier = Modifier.padding(it), navController = navController)
    }
}

/**
 * HomeMainContent.
 * This contains
 * ・Top Section
 * ・Dashboard Section for logged in user
 * ・Menu List Section
 */
@Composable
fun HomeMainContent(modifier: Modifier = Modifier, navController: NavController) {
    val viewModel: HomeViewModel = hiltViewModel()

    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        // Top Section
        TopSection()

        // Today's　Stats Section for logged in user
        if (viewModel.isLoggedIn) {
            Spacer(modifier = Modifier.height(20.dp))
            TodayStatsSection(modifier = Modifier.padding(horizontal = 20.dp))
        }

        // Menus List Section for logged in users
        Spacer(modifier = Modifier.height(20.dp))
        SingleMenusList(navController)
    }
}