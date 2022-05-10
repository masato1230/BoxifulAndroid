package com.jp_funda.boxiful.views.training.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jp_funda.boxiful.ui.theme.BlackAlpha50
import com.jp_funda.boxiful.ui.theme.Green500
import com.jp_funda.boxiful.views.training.TrainingViewModel

@Composable
fun CountDownOverlay() {
    val viewModel = hiltViewModel<TrainingViewModel>()
    val count = viewModel.countDownTime.collectAsState()
    val animatedIndicatorPercentage by animateFloatAsState(
        targetValue = count.value.toFloat(),
        animationSpec = tween(1000)
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Box(
            modifier = Modifier.background(BlackAlpha50),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = count.value.toString(), style = MaterialTheme.typography.h1)
            CircularProgressIndicator(
                color = Green500,
                modifier = Modifier.size(250.dp),
                progress = animatedIndicatorPercentage / TrainingViewModel.MAX_COUNT_DOWN_TIME,
                strokeWidth = 12.dp,
            )
        }
    }
}