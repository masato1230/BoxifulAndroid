package com.jp_funda.boxiful.views.training.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.ui.theme.BlackAlpha50
import com.jp_funda.boxiful.views.components.count_down_timer.CountDownTimer
import com.jp_funda.boxiful.views.training.TrainingViewModel

@Composable
fun CountDownOverlay() {
    val viewModel = hiltViewModel<TrainingViewModel>()
    val count = viewModel.countDownTime.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        CountDownTimer(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(BlackAlpha50)
                .padding(10.dp),
            maxValue = TrainingViewModel.MAX_COUNT_DOWN_TIME,
            remainingTime = count.value,
            beepSoundRes = R.raw.timer_beep,
        )
    }
}