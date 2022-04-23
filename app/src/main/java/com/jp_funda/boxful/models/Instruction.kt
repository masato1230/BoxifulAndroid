package com.jp_funda.boxful.models

import androidx.annotation.StringRes
import com.google.mlkit.vision.pose.Pose
import com.google.mlkit.vision.pose.PoseLandmark

/**
 * Punch or Kick instruction definition.
 */
enum class Instruction(
    @StringRes
    val titleRes: Int,
    val detectStartCallback: (allPoseLandmark: List<PoseLandmark>) -> Boolean,
    val detectEndCallback: (allPoseLandmark: List<PoseLandmark>) -> Boolean,
) {
    E();


    companion object {

        /**
         * Check all needed Landmarks is satisfying minimum in frame likely food.
         * @return is Satisfy
         */
        fun checkIsSatisfyMinimumInFrameLikelyFood(
            pose: Pose,
            checkLandmarkIds: List<Int>,
            minimumLikelyFood: Float = 0.6f,
        ) {
            checkLandmarkIds.all { id ->
                val landmark = pose.getPoseLandmark(id)
                landmark?.inFrameLikelihood ?: 0f < minimumLikelyFood
            }
        }
    }
}