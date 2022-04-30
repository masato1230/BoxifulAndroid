package com.jp_funda.boxiful.utils.scoring

import kotlin.math.roundToInt

object ScoreCalculator {
    /**
     * Calculate score for one movement of single menu.
     * @param time Duration of movement in seconds
     * @return score in range of 0 ~ 100 (actually min value is 20)
     */
    fun getSingleMenuMoveScore(time: Float): Int {
        val rawScore = 100 - (time - 0.65f) * 30
        val score = when {
            rawScore > 100 -> 100
            rawScore < 20 -> 20
            else -> rawScore.roundToInt()
        }
        return score
    }
}