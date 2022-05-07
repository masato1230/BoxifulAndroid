package com.jp_funda.boxiful.views.record

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.models.NetworkStatus
import com.jp_funda.boxiful.navigation.NavigationRoutes
import com.jp_funda.boxiful.views.components.ErrorView
import com.jp_funda.boxiful.views.components.LoadingView
import com.jp_funda.boxiful.views.components.header.Header
import com.jp_funda.boxiful.views.record.component.TotalStatsSection
import com.jp_funda.boxiful.views.record.component.WeeklyStatsSection

@Composable
fun RecordScreen(navController: NavController) {
    Scaffold(topBar = { Header(navController) }) {
        RecordMainContent(modifier = Modifier.padding(it), navController = navController)
    }
}

@Composable
fun RecordMainContent(modifier: Modifier = Modifier, navController: NavController) {
    val viewModel = hiltViewModel<RecordViewModel>()
    if (viewModel.isLoggedIn) {
        // If logged in, then fetch training results
        viewModel.getTrainingResults()
        val networkStatus = viewModel.networkStatus.observeAsState()

        when (networkStatus.value) {
            is NetworkStatus.Success -> {
                OnSuccessContent()
            }
            is NetworkStatus.Error -> {
                ErrorView(
                    navController = navController,
                    errorMessage = stringResource((networkStatus.value as NetworkStatus.Error).errorRes)
                )
            }
            else -> {
                LoadingView()
            }
        }
    } else {
        // When user is not Logged in
        ErrorView(
            navController = navController,
            errorMessage = stringResource(id = R.string.record_login_needed_to_recording),
            actionMessage = stringResource(id = R.string.record_login)
        ) {
            navController.navigate(NavigationRoutes.LOGIN)
        }
    }
}

@Composable
fun OnSuccessContent() {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Weekly Stats Section
        WeeklyStatsSection()
        Spacer(modifier = Modifier.height(20.dp))

        // Total Stats Section
        TotalStatsSection()

        Spacer(modifier = Modifier.height(50.dp))
    }
}