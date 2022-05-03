package com.jp_funda.boxiful.views.result

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.models.SingleMenuResult
import com.jp_funda.boxiful.ui.theme.Blue500
import com.jp_funda.boxiful.views.components.LabeledPieChart
import com.jp_funda.boxiful.views.components.header.Header
import com.jp_funda.boxiful.views.result.component.BoxifulAgeSection
import com.jp_funda.boxiful.views.result.component.OverallScoreSection
import com.jp_funda.boxiful.views.result.component.ResultDetailSection

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ResultScreen(navController: NavController, singleMenuResult: SingleMenuResult) {
    // Set result scores to viewModel
    hiltViewModel<ResultViewModel>().setSingleMenuScores(singleMenuResult)

    Scaffold(topBar = { Header(navController) }) {
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
        Spacer(modifier = Modifier.height(10.dp))
        // Section for boxiful age
        BoxifulAgeSection(viewModel.resultStats)
        Spacer(modifier = Modifier.height(10.dp))
        // Section for result detail
        ResultDetailSection(viewModel.resultStats)
        Spacer(modifier = Modifier.height(10.dp))
        // Punch and Kick score
        Row(modifier = Modifier.fillMaxWidth()) {
            // Section for punch score
            viewModel.punchScore?.let { score ->
                LabeledPieChart(
                    indicatorValue = score.toFloat(),
                    title = stringResource(id = R.string.result_punch_evaluation),
                    centerLabel = stringResource(id = R.string.result_unit_score, score),
                    modifier = Modifier.weight(0.5f),
                    indicatorColor = Blue500,
                )
            } ?: Spacer(modifier = Modifier.weight(0.5f))
            // Section for kick score
            viewModel.kickScore?.let { score ->
                LabeledPieChart(
                    indicatorValue = score.toFloat(),
                    title = stringResource(id = R.string.result_kick_evaluation),
                    centerLabel = stringResource(id = R.string.result_unit_score, score),
                    modifier = Modifier.weight(0.5f),
                    indicatorColor = Color.Yellow,
                )
            } ?: Spacer(modifier = Modifier.weight(0.5f))
        }
        // buffer for vertical scroll
        Spacer(modifier = Modifier.height(100.dp))
    }
}