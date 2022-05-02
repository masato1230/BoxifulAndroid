package com.jp_funda.boxiful.utils.scoring

import com.jp_funda.boxiful.models.Instruction
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

    /** Calculate overall score for single menu. */
    fun getSingleMenuOverallScore(scores: List<Int>): Int {
        val sortedScores = scores.sorted()
        val exceptOutlierScores = sortedScores.slice(3..scores.size - 3)
        return exceptOutlierScores.average().roundToInt()
    }

    /** Calculate boxiful age by single menu scores. */
    fun getBoxifulAge(score: Int): Int {
        var age = 110 - score
        if (age < 20) age = 20
        return age
    }

    /**
     * Calculate punch score.
     * @return return punch score. If punch instructions is empty, then return null.
     */
    fun getSingleMenuPunchScore(scores: List<Int>, instructions: List<Instruction>): Int? {
        val punchIndexes = instructions.mapIndexed { index, instruction ->
            if (Instruction.PUNCH_INSTRUCTIONS.contains(instruction)) index else null
        }.filterNotNull()

        if (punchIndexes.isEmpty()) return null
        val punchScores = punchIndexes.map { scores[it] }
        return getSingleMenuOverallScore(punchScores)
    }

    /**
     * Calculate kick score.
     * @return return kick score. If kick instructions is empty, then return null.
     */
    fun getSingleMenuKickScore(scores: List<Int>, instructions: List<Instruction>): Int? {
        val kickIndexes = instructions.mapIndexed { index, instruction ->
            if (Instruction.KICK_INSTRUCTIONS.contains(instruction)) index else null
        }.filterNotNull()

        if (kickIndexes.isEmpty()) return null
        val kickScores = kickIndexes.map { scores[it] }
        return getSingleMenuOverallScore(kickScores)
    }
}