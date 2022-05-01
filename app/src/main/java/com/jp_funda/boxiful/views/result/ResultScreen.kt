package com.jp_funda.boxiful.views.result

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jp_funda.boxiful.models.SingleMenuScores
import com.jp_funda.boxiful.views.components.Header
import com.jp_funda.boxiful.views.components.LabeledPieChart
import com.jp_funda.boxiful.views.result.component.BoxifulAgeSection
import com.jp_funda.boxiful.views.result.component.OverallScoreSection
import com.jp_funda.boxiful.views.result.component.ResultDetailSection

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ResultScreen(navController: NavController, singleMenuScores: SingleMenuScores) {
    // Set result scores to viewModel
    hiltViewModel<ResultViewModel>().setSingleMenuScores(singleMenuScores)

    Scaffold(topBar = { Header() }) {
        ResultMainContent(navController = navController)
    }
}

@Composable
fun ResultMainContent(navController: NavController) {
    val viewModel = hiltViewModel<ResultViewModel>()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
    ) {
        // Section for overall score
        OverallScoreSection(viewModel.singleMenuOverallScore)
        // Section for boxiful age
        BoxifulAgeSection()
        // Section for result detail
        ResultDetailSection()
        // Punch and Kick score
        Row(modifier = Modifier.fillMaxWidth()) {
            // Section for punch score
            LabeledPieChart(
                indicatorValue = 77f,
                title = "パンチ評価",
                centerLabel = "77点",
                modifier = Modifier.weight(0.5f),
            )
            // Section for kick score
            LabeledPieChart(
                indicatorValue = 93f,
                title = "キック評価",
                centerLabel = "93点",
                modifier = Modifier.weight(0.5f),
            )
        }
        // buffer for vertical scroll
        Spacer(modifier = Modifier.height(200.dp))
    }
}