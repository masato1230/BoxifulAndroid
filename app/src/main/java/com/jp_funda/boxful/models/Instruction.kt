package com.jp_funda.boxful.models

import android.graphics.PointF
import androidx.annotation.StringRes
import androidx.compose.ui.res.stringResource
import com.google.mlkit.vision.pose.Pose
import com.google.mlkit.vision.pose.PoseLandmark
import com.jp_funda.boxful.R
import com.jp_funda.boxful.utils.pose.AngleCalculator

/**
 * Punch or Kick instruction definition.
 */
enum class Instruction(
    @StringRes
    val titleRes: Int,
    val detectStartCallback: (allPoseLandmark: List<PoseLandmark>) -> Boolean,
    val detectEndCallback: (allPoseLandmark: List<PoseLandmark>) -> Boolean,
) {
    LeftArmLeftPunch(
        titleRes = stringResource(id = R.string.settings);


    companion object {

        /**
         * Check all needed Landmarks is satisfying minimum in frame likely food.
         * @return is Satisfy
         */
        private fun checkIsSatisfyMinimumInFrameLikelyFood(
            pose: Pose,
            checkLandmarkIds: List<Int>,
            minimumLikelyFood: Float = 0.6f,
        ): Boolean {
            return checkLandmarkIds.all { id ->
                val landmark = pose.getPoseLandmark(id)
                landmark?.inFrameLikelihood ?: 0f < minimumLikelyFood
            }
        }

        /**
         * Check left arm is ready to punch.
         * When left arm is folded, then it is ready to punch
         */
        fun checkIsLeftArmIsReadyToPunch(pose: Pose): Boolean {
            val isSatisfyMinimumInFlameLikelyFood =
                checkIsSatisfyMinimumInFrameLikelyFood(
                    pose = pose,
                    checkLandmarkIds = listOf(
                        PoseLandmark.LEFT_WRIST,
                        PoseLandmark.LEFT_ELBOW,
                        PoseLandmark.LEFT_SHOULDER,
                    )
                )
            return if (!isSatisfyMinimumInFlameLikelyFood) {
                false
            } else {
                val leftArmAngle = AngleCalculator.getAngle(
                    firstPoint = pose.getPoseLandmark(PoseLandmark.LEFT_WRIST)!!,
                    midPoint = pose.getPoseLandmark(PoseLandmark.LEFT_ELBOW)!!,
                    lastPoint = pose.getPoseLandmark(PoseLandmark.LEFT_SHOULDER)!!,
                )
                leftArmAngle <= 90
            }
        }

        /**
         * Check right arm is ready to punch.
         * When right arm is folded, then it is ready to punch
         */
        fun checkIsRightArmIsReadyToPunch(pose: Pose): Boolean {
            val isSatisfyMinimumInFlameLikelyFood =
                checkIsSatisfyMinimumInFrameLikelyFood(
                    pose = pose,
                    checkLandmarkIds = listOf(
                        PoseLandmark.RIGHT_WRIST,
                        PoseLandmark.RIGHT_ELBOW,
                        PoseLandmark.RIGHT_SHOULDER,
                    )
                )
            return if (!isSatisfyMinimumInFlameLikelyFood) {
                false
            } else {
                val rightArmAngle = AngleCalculator.getAngle(
                    firstPoint = pose.getPoseLandmark(PoseLandmark.RIGHT_WRIST)!!,
                    midPoint = pose.getPoseLandmark(PoseLandmark.RIGHT_ELBOW)!!,
                    lastPoint = pose.getPoseLandmark(PoseLandmark.RIGHT_SHOULDER)!!,
                )
                rightArmAngle <= 90
            }
        }

        /**
         * Check left leg is ready to kick.
         * When left leg is lowered, then it is ready to kick
         */
        fun checkIsLeftLegIsReadyToKick(pose: Pose): Boolean {
            val isSatisfyMinimumInFlameLikelyFood =
                checkIsSatisfyMinimumInFrameLikelyFood(
                    pose = pose,
                    checkLandmarkIds = listOf(
                        PoseLandmark.LEFT_KNEE,
                        PoseLandmark.LEFT_HIP,
                    )
                )
            return if (!isSatisfyMinimumInFlameLikelyFood) {
                false
            } else {
                val leftLegAngle = AngleCalculator.getAngle(
                    firstPoint = pose.getPoseLandmark(PoseLandmark.LEFT_KNEE)!!,
                    midPoint = pose.getPoseLandmark(PoseLandmark.LEFT_HIP)!!,
                    lastPoint = PointF(0f, Float.MAX_VALUE),
                )
                leftLegAngle < 30
            }
        }

        /**
         * Check right leg is ready to kick.
         * When right leg is lowered, then it is ready to kick
         */
        fun checkIsRightLegIsReadyToKick(pose: Pose): Boolean {
            val isSatisfyMinimumInFlameLikelyFood =
                checkIsSatisfyMinimumInFrameLikelyFood(
                    pose = pose,
                    checkLandmarkIds = listOf(
                        PoseLandmark.RIGHT_KNEE,
                        PoseLandmark.RIGHT_HIP,
                    )
                )
            return if (!isSatisfyMinimumInFlameLikelyFood) {
                false
            } else {
                val rightLegAngle = AngleCalculator.getAngle(
                    firstPoint = pose.getPoseLandmark(PoseLandmark.RIGHT_KNEE)!!,
                    midPoint = pose.getPoseLandmark(PoseLandmark.RIGHT_HIP)!!,
                    lastPoint = PointF(0f, Float.MAX_VALUE),
                )
                rightLegAngle < 30
            }
        }
    }
}