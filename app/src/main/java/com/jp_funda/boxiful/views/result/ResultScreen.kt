package com.jp_funda.boxiful.views.result

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jp_funda.boxiful.models.SingleMenuScores

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ResultScreen(navController: NavController, singleMenuScores: SingleMenuScores) {
    // Set result scores to viewModel
    hiltViewModel<ResultViewModel>().setSingleMenuScores(singleMenuScores)

    Scaffold {
        ResultMainContent(navController = navController)
    }
}

@Composable
fun ResultMainContent(navController: NavController) {
    val viewModel = hiltViewModel<ResultViewModel>()
    Text(text = "Hello")
}