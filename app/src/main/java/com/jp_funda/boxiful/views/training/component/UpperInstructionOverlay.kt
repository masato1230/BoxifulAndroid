package com.jp_funda.boxiful.views.training.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jp_funda.boxiful.models.Instruction
import com.jp_funda.boxiful.ui.theme.BlackAlpha50

@Composable
fun UpperInstructionOverlay(instructionIndex: Int, instructions: List<Instruction>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(BlackAlpha50)
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.Center,
    ) {

    }
}