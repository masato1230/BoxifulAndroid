package com.jp_funda.boxiful.views.training.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jp_funda.boxiful.models.Instruction
import com.jp_funda.boxiful.ui.theme.BlackAlpha50
import com.jp_funda.boxiful.ui.theme.Yellow500
import com.jp_funda.boxiful.views.components.AnimatedProgressIndicator

@Composable
fun UpperInstructionOverlay(
    title: String,
    instructionIndex: Int,
    instructions: List<Instruction>,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(BlackAlpha50)
            .padding(10.dp),
    ) {
        Text(
            text = title,
            color = Color.White,
            fontWeight = FontWeight.ExtraBold,
            style = MaterialTheme.typography.h6
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AnimatedProgressIndicator(
                modifier = Modifier
                    .weight(1f),
                progress = instructionIndex.toFloat() / (instructions.size - 1),
                indicatorColor = Yellow500,
                backgroundIndicatorColor = Color.DarkGray.copy(alpha = 0.8f),
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = "$instructionIndex / ${instructions.size}",
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
            )
        }
    }
}