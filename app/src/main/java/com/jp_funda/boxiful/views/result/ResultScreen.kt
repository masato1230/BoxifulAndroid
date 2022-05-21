package com.jp_funda.boxiful.views.result

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.models.SingleMenuResult
import com.jp_funda.boxiful.ui.theme.Background
import com.jp_funda.boxiful.ui.theme.Blue500
import com.jp_funda.boxiful.views.MainActivity
import com.jp_funda.boxiful.views.components.LabeledPieChart
import com.jp_funda.boxiful.views.components.header.Header
import com.jp_funda.boxiful.views.result.component.BoxifulAgeSection
import com.jp_funda.boxiful.views.result.component.OverallScoreSection
import com.jp_funda.boxiful.views.result.component.ResultDetailSection
import kotlinx.coroutines.delay

@ExperimentalPermissionsApi
@ExperimentalAnimationApi
@ExperimentalPagerApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ResultScreen(navController: NavController, singleMenuResult: SingleMenuResult) {
    // Set result scores to viewModel
    hiltViewModel<ResultViewModel>().setSingleMenuScores(singleMenuResult)

    // Show interstitial Ad
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        delay(500)
        (context as MainActivity).showInterstitial()
    }

    Scaffold(topBar = { Header(navController) }) {
        Background()
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
            if (viewModel.punchScore != null) {
                LabeledPieChart(
                    indicatorValue = viewModel.punchScore!!.toFloat(),
                    title = stringResource(id = R.string.result_punch_evaluation),
                    centerLabel = stringResource(
                        id = R.string.result_unit_score,
                        viewModel.punchScore!!
                    ),
                    modifier = Modifier.weight(0.5f),
                    indicatorColor = Blue500,
                )
            } else {
                Spacer(modifier = Modifier.weight(0.5f))
            }
            // Section for kick score
            if (viewModel.kickScore != null) {
                LabeledPieChart(
                    indicatorValue = viewModel.kickScore!!.toFloat(),
                    title = stringResource(id = R.string.result_kick_evaluation),
                    centerLabel = stringResource(
                        id = R.string.result_unit_score,
                        viewModel.kickScore!!
                    ),
                    modifier = Modifier.weight(0.5f),
                    indicatorColor = Color.Yellow,
                )
            } else {
                Spacer(modifier = Modifier.weight(0.5f))
            }
        }
        // buffer for vertical scroll
        Spacer(modifier = Modifier.height(100.dp))
    }
}