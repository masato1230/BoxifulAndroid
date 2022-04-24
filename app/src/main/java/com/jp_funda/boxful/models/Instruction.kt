package com.jp_funda.boxful.models

import android.graphics.PointF
import androidx.annotation.StringRes
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
    LeftHandLeftPunch(
        titleRes = R.string.instructions_left_hand_left_punch,
        detectStartCallback = { checkLeftArmIsFolded(it) },
        detectEndCallback = {
            checkLeftArmPunchIsEnd(pose = it, direction = HorizontalDirection.LEFT)
        },
    ),
    LeftHandRightPunch(
        titleRes = R.string.instructions_left_hand_right_punch,
        detectStartCallback = { checkLeftArmIsFolded(it) },
        detectEndCallback = {
            checkLeftArmPunchIsEnd(pose = it, direction = HorizontalDirection.RIGHT)
        },
    ),
    LeftFootLeftKick(
        titleRes = R.string.instructions_left_foot_left_kick,
        detectStartCallback = { checkLeftLegIsLowered(it) },
        detectEndCallback = {
            checkLeftLegKickIsEnd(pose = it, direction = HorizontalDirection.LEFT)
        },
    ),
    LeftFootRightKick(
        titleRes = R.string.instructions_left_foot_right_kick,
        detectStartCallback = { checkLeftLegIsLowered(it) },
        detectEndCallback = {
            checkLeftLegKickIsEnd(pose = it, direction = HorizontalDirection.RIGHT)
        },
    ),
    RightHandLeftPunch(
        titleRes = R.string.instructions_right_hand_left_punch,
        detectStartCallback = { checkRightArmIsFolded(it) },
        detectEndCallback = {
            checkRightArmPunchIsEnd(pose = it, direction = HorizontalDirection.LEFT)
        },
    ),
    RightHandRightPunch(
        titleRes = R.string.instructions_right_hand_right_punch,
        detectStartCallback = { checkRightArmIsFolded(it) },
        detectEndCallback = {
            checkRightArmPunchIsEnd(pose = it, direction = HorizontalDirection.RIGHT)
        },
    ),
    RightFootLeftKick(
        titleRes = R.string.instructions_right_foot_left_kick,
        detectStartCallback = { checkRightLegIsLowered(it) },
        detectEndCallback = {
            checkRightLegKickIsEnd(pose = it, direction = HorizontalDirection.LEFT)
        },
    ),
    RightFootRightKick(
        titleRes = R.string.instructions_right_foot_right_kick,
        detectStartCallback = { checkRightLegIsLowered(it) },
        detectEndCallback = {
            checkRightLegKickIsEnd(pose = it, direction = HorizontalDirection.RIGHT)
        },
    );


    companion object {
        /**
         * Check if left arm is at punch end state.
         */
        fun checkLeftArmPunchIsEnd(pose: Pose, direction: HorizontalDirection): Boolean {
            val isStretched = checkLeftArmIsStretched(pose)
            if (!isStretched) return false

            return direction == getHorizontalVectorDirection(
                from = pose.getPoseLandmark(PoseLandmark.LEFT_ELBOW)!!,
                to = pose.getPoseLandmark(PoseLandmark.LEFT_WRIST)!!,
            )
        }

        /**
         * Check if right arm is at punch end state.
         */
        fun checkRightArmPunchIsEnd(pose: Pose, direction: HorizontalDirection): Boolean {
            val isStretched = checkRightArmIsStretched(pose)
            if (!isStretched) return false

            return direction == getHorizontalVectorDirection(
                from = pose.getPoseLandmark(PoseLandmark.RIGHT_ELBOW)!!,
                to = pose.getPoseLandmark(PoseLandmark.RIGHT_WRIST)!!,
            )
        }

        /**
         * Check if left leg is at kick end state.
         */
        fun checkLeftLegKickIsEnd(pose: Pose, direction: HorizontalDirection): Boolean {
            val isStretched = checkLeftLegIsStretched(pose)
            if (!isStretched) return false

            return direction == getHorizontalVectorDirection(
                from = pose.getPoseLandmark(PoseLandmark.LEFT_HIP)!!,
                to = pose.getPoseLandmark(PoseLandmark.LEFT_KNEE)!!,
            )
        }

        /**
         * Check if right leg is at end state.
         */
        fun checkRightLegKickIsEnd(pose: Pose, direction: HorizontalDirection): Boolean {
            val isStretched = checkRightLegIsStretched(pose)
            if (!isStretched) return false

            return direction == getHorizontalVectorDirection(
                from = pose.getPoseLandmark(PoseLandmark.RIGHT_HIP)!!,
                to = pose.getPoseLandmark(PoseLandmark.RIGHT_KNEE)!!,
            )
        }

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
        fun checkRightArmIsFolded(pose: Pose): Boolean {
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
        fun checkLeftLegIsLowered(pose: Pose): Boolean {
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
        fun checkRightLegIsLowered(pose: Pose): Boolean {
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
        fun checkLeftArmIsStretched(pose: Pose): Boolean {
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
        fun checkRightArmIsStretched(pose: Pose): Boolean {
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
        fun checkLeftLegIsStretched(pose: Pose): Boolean {
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
        fun checkRightLegIsStretched(pose: Pose): Boolean {
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

        /**
         * Get Horizontal Vector direction.
         */
        private fun getHorizontalVectorDirection(
            from: PoseLandmark,
            to: PoseLandmark,
        ): HorizontalDirection {
            val horizontalVector = to.position.x - from.position.x
            return if (horizontalVector > 0) {
                HorizontalDirection.LEFT
            } else HorizontalDirection.RIGHT
        }
    }

    enum class HorizontalDirection {
        LEFT,
        RIGHT;
    }
}