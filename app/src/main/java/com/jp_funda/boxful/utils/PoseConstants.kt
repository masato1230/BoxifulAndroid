package com.jp_funda.boxful.utils

import com.google.mlkit.vision.pose.PoseLandmark

object PoseConstants {
    /** Landmarks for left side graphic. */
    val LEFT_SIDE_LANDMARKS = setOf(
        PoseLandmark.LEFT_WRIST,
        PoseLandmark.LEFT_ELBOW,
        PoseLandmark.LEFT_SHOULDER,
        PoseLandmark.LEFT_HIP,
        PoseLandmark.LEFT_KNEE,
        PoseLandmark.LEFT_ANKLE,
        PoseLandmark.LEFT_PINKY,
        PoseLandmark.LEFT_INDEX,
        PoseLandmark.LEFT_THUMB,
        PoseLandmark.LEFT_HEEL,
        PoseLandmark.LEFT_FOOT_INDEX,
    )
    
    /** Landmarks for right side graphic. */
    val RIGHT_SIDE_LANDMARKS = setOf(
        PoseLandmark.RIGHT_WRIST,
        PoseLandmark.RIGHT_ELBOW,
        PoseLandmark.RIGHT_SHOULDER,
        PoseLandmark.RIGHT_HIP,
        PoseLandmark.RIGHT_KNEE,
        PoseLandmark.RIGHT_ANKLE,
        PoseLandmark.RIGHT_PINKY,
        PoseLandmark.RIGHT_INDEX,
        PoseLandmark.RIGHT_THUMB,
        PoseLandmark.RIGHT_HEEL,
        PoseLandmark.RIGHT_FOOT_INDEX,
    )

    /** Landmarks for middle side graphic. */
    val MIDDLE_SIDE_LANDMARKS = setOf(
        PoseLandmark.NOSE,
    )
    
    /** Landmark pairs for graphic bones. */
    val BONE_LANDMARK_SETS = setOf(
        // Left side bones
        Pair(PoseLandmark.LEFT_WRIST, PoseLandmark.LEFT_ELBOW),
        Pair(PoseLandmark.LEFT_ELBOW, PoseLandmark.LEFT_SHOULDER),
        Pair(PoseLandmark.LEFT_SHOULDER, PoseLandmark.LEFT_HIP),
        Pair(PoseLandmark.LEFT_HIP, PoseLandmark.LEFT_KNEE),
        Pair(PoseLandmark.LEFT_KNEE, PoseLandmark.LEFT_ANKLE),
        Pair(PoseLandmark.LEFT_THUMB, PoseLandmark.LEFT_WRIST),
        Pair(PoseLandmark.LEFT_INDEX, PoseLandmark.LEFT_WRIST),
        Pair(PoseLandmark.LEFT_PINKY, PoseLandmark.LEFT_WRIST),
        Pair(PoseLandmark.LEFT_FOOT_INDEX, PoseLandmark.LEFT_ANKLE),
        Pair(PoseLandmark.LEFT_HEEL, PoseLandmark.LEFT_ANKLE),
        Pair(PoseLandmark.LEFT_FOOT_INDEX, PoseLandmark.LEFT_HEEL),
        // Right side bones
        Pair(PoseLandmark.RIGHT_WRIST, PoseLandmark.RIGHT_ELBOW),
        Pair(PoseLandmark.RIGHT_ELBOW, PoseLandmark.RIGHT_SHOULDER),
        Pair(PoseLandmark.RIGHT_SHOULDER, PoseLandmark.RIGHT_HIP),
        Pair(PoseLandmark.RIGHT_HIP, PoseLandmark.RIGHT_KNEE),
        Pair(PoseLandmark.RIGHT_KNEE, PoseLandmark.RIGHT_ANKLE),
        Pair(PoseLandmark.RIGHT_THUMB, PoseLandmark.RIGHT_WRIST),
        Pair(PoseLandmark.RIGHT_INDEX, PoseLandmark.RIGHT_WRIST),
        Pair(PoseLandmark.RIGHT_PINKY, PoseLandmark.RIGHT_WRIST),
        Pair(PoseLandmark.RIGHT_FOOT_INDEX, PoseLandmark.RIGHT_ANKLE),
        Pair(PoseLandmark.RIGHT_HEEL, PoseLandmark.RIGHT_ANKLE),
        Pair(PoseLandmark.RIGHT_FOOT_INDEX, PoseLandmark.RIGHT_HEEL),
        // Middle side bones
        Pair(PoseLandmark.LEFT_SHOULDER, PoseLandmark.RIGHT_SHOULDER),
        Pair(PoseLandmark.LEFT_HIP, PoseLandmark.RIGHT_HIP),
    )
}