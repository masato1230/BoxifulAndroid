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
    val detectStartCallback: (pose: Pose) -> Boolean,
    val detectEndCallback: (pose: Pose) -> Boolean,
) {
    LeftArmLeftPunch(
        titleRes = R.string.instructions_left_hand_left_punch,
        detectStartCallback = { checkIsLeftArmIsReadyToPunch(it) },
        detectEndCallback = { checkIs }
    )
    ;


    companion object {

        /**
         * Check all needed Landmarks is satisfying minimum in frame likely food.
         * @return is Satisfy
         */
        private fun checkIsSatisfyMinimumInFrameLikelyFood(
            pose: Pose,
            checkLandmarkIds: Set<Int>,
            minimumLikelyFood: Float = 0.6f,
        ): Boolean {
            return checkLandmarkIds.all { id ->
                val landmark = pose.getPoseLandmark(id)
                landmark?.inFrameLikelihood ?: 0f < minimumLikelyFood
            }
        }

        /**
         * Check if the left arm is Folded.
         * When left arm is folded, then it is ready to punch
         */
        fun checkLeftArmIsFolded(pose: Pose): Boolean {
            val isSatisfyMinimumInFlameLikelyFood =
                checkIsSatisfyMinimumInFrameLikelyFood(
                    pose = pose,
                    checkLandmarkIds = setOf(
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
         * Check if the right arm is Folded.
         * When right arm is folded, then it is ready to punch
         */
        fun checkIsRightArmIsFolded(pose: Pose): Boolean {
            val isSatisfyMinimumInFlameLikelyFood =
                checkIsSatisfyMinimumInFrameLikelyFood(
                    pose = pose,
                    checkLandmarkIds = setOf(
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
         * Check if the left leg is lowered.
         * When left leg is lowered, then it is ready to kick
         */
        fun checkIsLeftLegIsLowered(pose: Pose): Boolean {
            val isSatisfyMinimumInFlameLikelyFood =
                checkIsSatisfyMinimumInFrameLikelyFood(
                    pose = pose,
                    checkLandmarkIds = setOf(
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
         * Check if the right leg is lowered.
         * When right leg is lowered, then it is ready to kick
         */
        fun checkIsRightLegIsLowered(pose: Pose): Boolean {
            val isSatisfyMinimumInFlameLikelyFood =
                checkIsSatisfyMinimumInFrameLikelyFood(
                    pose = pose,
                    checkLandmarkIds = setOf(
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

        /**
         * Check if the left arm is stretched.
         */
        fun checkIsLeftArmIsStretched(pose: Pose): Boolean {
            val isSatisfyMinimumInFlameLikelyFood =
                checkIsSatisfyMinimumInFrameLikelyFood(
                    pose = pose,
                    checkLandmarkIds = setOf(
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
                return leftArmAngle >= 130
            }
        }

        /**
         * Check if the right arm is stretched.
         */
        fun checkIsRightArmIsStretched(pose: Pose): Boolean {
            val isSatisfyMinimumInFrameLikelyFood =
                checkIsSatisfyMinimumInFrameLikelyFood(
                    pose = pose,
                    checkLandmarkIds = setOf(
                        PoseLandmark.RIGHT_WRIST,
                        PoseLandmark.RIGHT_ELBOW,
                        PoseLandmark.RIGHT_SHOULDER,
                    )
                )
            return if (!isSatisfyMinimumInFrameLikelyFood) {
                false
            } else {
                val rightArmAngle = AngleCalculator.getAngle(
                    firstPoint = pose.getPoseLandmark(PoseLandmark.RIGHT_WRIST)!!,
                    midPoint = pose.getPoseLandmark(PoseLandmark.RIGHT_ELBOW)!!,
                    lastPoint = pose.getPoseLandmark(PoseLandmark.RIGHT_SHOULDER)!!,
                )
                return rightArmAngle >= 130
            }
        }

        /**
         * Check if the left leg is stretched.
         */
        fun checkIsLeftLegIsStretched(pose: Pose): Boolean {
            val isSatisfyMinimumInFrameLikelyFood =
                checkIsSatisfyMinimumInFrameLikelyFood(
                    pose = pose,
                    checkLandmarkIds = setOf(
                        PoseLandmark.LEFT_KNEE,
                        PoseLandmark.LEFT_HIP,
                    )
                )
            return if (!isSatisfyMinimumInFrameLikelyFood) {
                false
            } else {
                val leftLegAngle = AngleCalculator.getAngle(
                    firstPoint = pose.getPoseLandmark(PoseLandmark.LEFT_KNEE)!!,
                    midPoint = pose.getPoseLandmark(PoseLandmark.LEFT_HIP)!!,
                    lastPoint = PointF(0f, Float.MAX_VALUE),
                )
                return leftLegAngle > 60
            }
        }

        /**
         * Check if the right leg is stretched.
         */
        fun checkIsRightLegIsStretched(pose: Pose): Boolean {
            val isSatisfyMinimumInFrameLikelyFood =
                checkIsSatisfyMinimumInFrameLikelyFood(
                    pose = pose,
                    checkLandmarkIds = setOf(
                        PoseLandmark.LEFT_KNEE,
                        PoseLandmark.LEFT_HIP,
                    )
                )
            return if (!isSatisfyMinimumInFrameLikelyFood) {
                false
            } else {
                val rightLegAngle = AngleCalculator.getAngle(
                    firstPoint = pose.getPoseLandmark(PoseLandmark.RIGHT_KNEE)!!,
                    midPoint = pose.getPoseLandmark(PoseLandmark.RIGHT_HIP)!!,
                    lastPoint = PointF(0f, Float.MAX_VALUE),
                )
                return rightLegAngle > 60
            }
        }

        // TODO left leg stretch
    }
}