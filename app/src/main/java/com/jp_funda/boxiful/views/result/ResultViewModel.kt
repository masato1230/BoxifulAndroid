package com.jp_funda.boxiful.views.result

import androidx.lifecycle.ViewModel
import com.jp_funda.boxiful.AppConst
import com.jp_funda.boxiful.models.ResultStats
import com.jp_funda.boxiful.models.SingleMenuResult
import com.jp_funda.boxiful.utils.calculator.CalorieCalculator
import com.jp_funda.boxiful.utils.calculator.ScoreCalculator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor() : ViewModel() {
    /** Single Menu Scores. */
    private lateinit var singleMenuResult: SingleMenuResult

    /** Single Menu overall score. */
    val singleMenuOverallScore: Int
        get() {
            return ScoreCalculator.getSingleMenuOverallScore(singleMenuResult.scores)
        }

    /** Setter for single menu scores. */
    fun setSingleMenuScores(result: SingleMenuResult) {
        singleMenuResult = result
    }

    /** Training Result Stats for showing result detail. */
    val resultStats: ResultStats
        get() {
            return ResultStats(
                greatCount = singleMenuResult.scores.count { it > AppConst.MAX_GOOD_SCORE },
                goodCount = singleMenuResult.scores.count { it in AppConst.MIN_GOOD_SCORE..AppConst.MAX_GOOD_SCORE },
                missCount = singleMenuResult.scores.count { it < AppConst.MIN_GOOD_SCORE },
                caloriesBurned = CalorieCalculator.getCaloriesBurned(singleMenuResult.instructions),
                boxifulAge = ScoreCalculator.getBoxifulAge(singleMenuOverallScore),
            )
        }

    /** Punch Score. Null means no punch instruction is exist. */
    val punchScore: Int?
        get() {
            return ScoreCalculator.getSingleMenuPunchScore(
                scores = singleMenuResult.scores,
                instructions = singleMenuResult.instructions,
            )
        }

    /** Kick Score. Null means no kick instruction is exist. */
    val kickScore: Int?
        get() {
            return ScoreCalculator.getSingleMenuKickScore(
                scores = singleMenuResult.scores,
                instructions = singleMenuResult.instructions,
            )
        }
}