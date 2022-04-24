package com.jp_funda.boxful.models

import android.graphics.PointF
import com.google.mlkit.vision.pose.PoseLandmark

/**
 * TODO delete
 * Enum for representing joints which angle is needed to be calculated
 * @param firstLandmark LandmarkType of first landmark
 * @param midLandmark LandmarkType of mid landmark
 * @param lastLandmark LandmarkType of lastPoint. when this field is null we will use lastPosition
 * @param lastPoint position of not landmark point
 */
enum class JointInfo(
    val firstLandmark: Int,
    val midLandmark: Int,
    val lastLandmark: Int?,
    val lastPoint: PointF?,
) {
    LeftElbowJoint(
        firstLandmark = PoseLandmark.LEFT_WRIST,
        midLandmark = PoseLandmark.LEFT_ELBOW,
        lastLandmark = PoseLandmark.LEFT_SHOULDER,
        lastPoint = null,
    ),
    LeftHipJoint(
        firstLandmark = PoseLandmark.LEFT_KNEE,
        midLandmark = PoseLandmark.LEFT_HIP,
        lastLandmark = null,
        lastPoint = PointF(0f, Float.MAX_VALUE),
    ),
    RightElbowJoint(
        firstLandmark = PoseLandmark.RIGHT_WRIST,
        midLandmark = PoseLandmark.RIGHT_ELBOW,
        lastLandmark = PoseLandmark.RIGHT_SHOULDER,
        lastPoint = null,
    ),
    RightHipJoint(
        firstLandmark = PoseLandmark.RIGHT_WRIST,
        midLandmark = PoseLandmark.RIGHT_HIP,
        lastLandmark = null,
        lastPoint = PointF(0f, Float.MAX_VALUE),
    )
}