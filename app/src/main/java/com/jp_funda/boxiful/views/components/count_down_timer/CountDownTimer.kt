package com.jp_funda.boxiful.views.components.count_down_timer

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jp_funda.boxiful.ui.theme.Green500

@Composable
fun CountDownTimer(
    modifier: Modifier = Modifier,
    maxValue: Int,
    remainingTime: Int,
    indicatorAnimationSpec: AnimationSpec<Float> = tween(1000),
) {
    val animatedIndicatorPercentage by animateFloatAsState(
        targetValue = remainingTime.toFloat(),
        animationSpec = indicatorAnimationSpec,
    )

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Text(text = remainingTime.toString(), style = MaterialTheme.typography.h1)
        CircularProgressIndicator(
            color = Green500,
            modifier = Modifier.size(250.dp),
            progress = animatedIndicatorPercentage / maxValue,
            strokeWidth = 12.dp,
        )
    }
}