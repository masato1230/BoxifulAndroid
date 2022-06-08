package com.jp_funda.boxiful.views.single_training.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jp_funda.boxiful.models.Instruction

/**
 * Instruction overlay contents.
 */
@Composable
fun InstructionOverlay(title: String, instructionIndex: Int, instructions: List<Instruction>) {
    val currentInstruction = instructions[instructionIndex]

    Column(modifier = Modifier.fillMaxSize()) {
        // Upper overlay
        UpperInstructionOverlay(title, instructionIndex, instructions)

        Spacer(modifier = Modifier.weight(1f))

        // Bottom overlay
        BottomInstructionOverlay(instructionIndex, currentInstruction)
    }
}