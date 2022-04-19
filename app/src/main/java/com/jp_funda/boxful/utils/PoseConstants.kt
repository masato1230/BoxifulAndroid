package com.jp_funda.boxful.utils

import com.google.mlkit.vision.pose.PoseLandmark

object PoseConstants {
    val BONE_LANDMARK_SETS = setOf(
        Pair(PoseLandmark.LEFT_WRIST, PoseLandmark.LEFT_ELBOW),
        Pair(PoseLandmark.LEFT_ELBOW, PoseLandmark.LEFT_SHOULDER),
    )
}