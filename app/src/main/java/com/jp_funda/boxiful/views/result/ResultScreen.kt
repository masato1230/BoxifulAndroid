package com.jp_funda.boxiful.views.result

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.models.SingleMenuScores
import com.jp_funda.boxiful.views.components.Header
import com.jp_funda.boxiful.views.components.LabeledPieChart
import com.jp_funda.boxiful.views.result.component.BoxifulAgeSection
import com.jp_funda.boxiful.views.result.component.OverallScoreSection
import com.jp_funda.boxiful.views.result.component.ResultDetailSection
import kotlin.math.roundToInt

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
        Spacer(modifier = Modifier.height(10.dp))
        // Section for boxiful age
        BoxifulAgeSection(viewModel.boxifulAge)
        Spacer(modifier = Modifier.height(10.dp))
        // Section for result detail
        ResultDetailSection(viewModel.resultStats)
        // Punch and Kick score
        Row(modifier = Modifier.fillMaxWidth()) {
            // Section for punch score
            val punchScore = 77f
            LabeledPieChart(
                indicatorValue = punchScore,
                title = stringResource(id = R.string.result_punch_evaluation),
                centerLabel = stringResource(id = R.string.result_unit_score, punchScore.roundToInt()),
                modifier = Modifier.weight(0.5f),
            )
            // Section for kick score
            val kickScore = 93f
            LabeledPieChart(
                indicatorValue = kickScore,
                title = stringResource(id = R.string.result_kick_evaluation),
                centerLabel = stringResource(id = R.string.result_kick_evaluation, kickScore.roundToInt()),
                modifier = Modifier.weight(0.5f),
            )
        }
        // buffer for vertical scroll
        Spacer(modifier = Modifier.height(100.dp))
    }
}