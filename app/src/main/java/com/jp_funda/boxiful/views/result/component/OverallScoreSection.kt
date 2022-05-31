package com.jp_funda.boxiful.views.result.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.ui.theme.BlackAlpha50
import com.jp_funda.boxiful.ui.theme.Pink500
import com.jp_funda.boxiful.views.components.AnimatedPieChart
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun OverallScoreSection(score: Int) {
    val animatedIndicatorValue = remember { Animatable(0f) }
    val composableScope = rememberCoroutineScope()
    LaunchedEffect(key1 = score) {
        animatedIndicatorValue.animateTo(0f)
        composableScope.launch {
            delay(500)
            animatedIndicatorValue.animateTo(score.toFloat())
        }
    }
    val animatedScore by animateIntAsState(
        targetValue = (animatedIndicatorValue.value).roundToInt(),
        animationSpec = tween(1000),
    )

    Column {
        Text(
            text = stringResource(id = R.string.result_training_evaluation),
            color = Color.White,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(top = 10.dp),
        )
        Spacer(modifier = Modifier.height(5.dp))
        Box(
            modifier = Modifier
                .clip(MaterialTheme.shapes.medium)
                .fillMaxWidth()
                .background(BlackAlpha50),
            contentAlignment = Alignment.Center,
        ) {
            AnimatedPieChart(
                indicatorValue = score.toFloat(),
                indicatorColor = Pink500,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 70.dp, vertical = 10.dp),
                indicatorStrokeWidth = 100f,
                backgroundIndicator = Color.Transparent,
            )
            Text(
                text = stringResource(id = R.string.result_unit_score, animatedScore),
                color = Color.White,
                style = MaterialTheme.typography.h3
            )
        }
    }
}