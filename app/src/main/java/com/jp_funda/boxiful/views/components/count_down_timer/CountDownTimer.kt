package com.jp_funda.boxiful.views.components.count_down_timer

import android.media.MediaPlayer
import androidx.annotation.RawRes
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.jp_funda.boxiful.ui.theme.Green500
import java.util.*
import kotlin.math.absoluteValue

@Composable
fun CountDownTimer(
    modifier: Modifier = Modifier,
    maxValue: Int,
    remainingTime: Int,
    indicatorAnimationSpec: AnimationSpec<Float> = tween(1000),
    @RawRes beepSoundRes: Int? = null,
    @RawRes finishSoundRes: Int? = null,
) {
    val context = LocalContext.current
    val animatedIndicatorPercentage by animateFloatAsState(
        targetValue = remainingTime.toFloat(),
        animationSpec = indicatorAnimationSpec,
    )

    // Sound effect management
    if (beepSoundRes != null) {
        val beepSound = remember { MediaPlayer.create(context, beepSoundRes) }
        val finishSound = remember { MediaPlayer.create(context, finishSoundRes ?: beepSoundRes) }

        val previousRemainingTime = remember { mutableStateOf(0) }
        val previousSoundPlayTime = remember { mutableStateOf(0L) }
        // Play Sound Effect
        if ((previousSoundPlayTime.value - Calendar.getInstance().timeInMillis).absoluteValue >= 1000) {
            when (remainingTime) {
                0 -> finishSound.start()
                maxValue -> {}
                else -> beepSound.start()
            }
            previousSoundPlayTime.value = Calendar.getInstance().timeInMillis
        }
        previousRemainingTime.value = remainingTime
    }

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