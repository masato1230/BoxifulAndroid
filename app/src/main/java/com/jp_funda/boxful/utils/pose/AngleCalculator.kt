package com.jp_funda.boxful.utils.pose

import com.google.mlkit.vision.pose.PoseLandmark
import kotlin.math.abs
import kotlin.math.atan2

object AngleCalculator {
    /**
     *  Get angle for joint.
     *  @return value of calculated angle which is in range of 0 ~ 180 degree
     * */
    fun getAngle(
        firstPoint: PoseLandmark,
        midPoint: PoseLandmark,
        lastPoint: PoseLandmark
    ): Float {
        var result = Math.toDegrees(
            (atan2(
                lastPoint.position.y - midPoint.position.y,
                lastPoint.position.x - midPoint.position.x
            ) - atan2(
                firstPoint.position.y - midPoint.position.y,
                firstPoint.position.x - midPoint.position.x
            )).toDouble()
        )
        result = abs(result) // Angle should never be negative
        if (result > 180) {
            result = 360.0 - result // Always get the acute representation of the angle
        }
        return result.toFloat()
    }
}