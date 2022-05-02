package com.jp_funda.boxiful.views.result

import androidx.lifecycle.ViewModel
import com.jp_funda.boxiful.AppConst
import com.jp_funda.boxiful.models.ResultStats
import com.jp_funda.boxiful.models.SingleMenuScores
import com.jp_funda.boxiful.utils.scoring.ScoreCalculator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor() : ViewModel() {
    /** Single Menu Scores. */
    private lateinit var singleMenuScores: SingleMenuScores

    /** Single Menu overall score. */
    val singleMenuOverallScore: Int
        get() {
            return ScoreCalculator.getSingleMenuOverallScore(singleMenuScores.scores)
        }

    /** Boxiful Age calculated by single menu scores(overall score). */
    val boxifulAge: Int
        get() {
            return ScoreCalculator.getBoxifulAge(singleMenuOverallScore)
        }

    /** Setter for single menu scores. */
    fun setSingleMenuScores(scores: SingleMenuScores) {
        singleMenuScores = scores
    }

    /** Training Result Stats for showing result detail. */
    val resultStats: ResultStats
        get() {
            return ResultStats(
                greatCount = singleMenuScores.scores.count { it > AppConst.MAX_GOOD_SCORE },
                goodCount = singleMenuScores.scores.count { it in AppConst.MIN_GOOD_SCORE..AppConst.MAX_GOOD_SCORE },
                missCount = singleMenuScores.scores.count { it < AppConst.MIN_GOOD_SCORE },
                caloriesBurned = singleMenuScores.singleMenu.calorieConsumption,
            )
        }
}