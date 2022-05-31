package com.jp_funda.boxiful.utils.calculator

import com.jp_funda.boxiful.models.Instruction
import kotlin.math.roundToInt

object CalorieCalculator {
    /** Calculate calories burned. */
    fun getCaloriesBurned(instructions: List<Instruction>): Int {
        return instructions.sumOf {
            if (Instruction.PUNCH_INSTRUCTIONS.contains(it)) 0.3 else 0.6
        }.roundToInt()
    }
}