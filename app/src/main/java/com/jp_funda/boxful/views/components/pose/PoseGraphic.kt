package com.jp_funda.boxful.views.components.pose

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jp_funda.boxful.utils.pose.AngleCalculator
import com.jp_funda.boxful.utils.pose.JointInfo
import com.jp_funda.boxful.utils.pose.PoseConstants

@Composable
fun PoseGraphic(poseViewModel: PoseViewModel = viewModel()) {
    val observedPose by poseViewModel.pose.observeAsState()

    val screenWidthDp = LocalConfiguration.current.screenWidthDp
    val screenHeightDp = LocalConfiguration.current.screenHeightDp

    observedPose?.let { pose ->
        with(LocalDensity.current) {
            val scaleFactor =
                (screenHeightDp * density) / poseViewModel.imageAnalysisResolution.height
            val offsetXDp =
                (screenHeightDp * poseViewModel.imageAnalysisResolution.width / poseViewModel.imageAnalysisResolution.height - screenWidthDp) / 2 - 10

            Canvas(modifier = Modifier.fillMaxSize()) {
                // Draw all bones
                for (bonePair in PoseConstants.BONE_LANDMARK_SETS) {
                    val startLandmark = pose.getPoseLandmark(bonePair.first)
                    val endLandmark = pose.getPoseLandmark(bonePair.second)

                    if (startLandmark == null || endLandmark == null) continue
                    drawLine(
                        color = Color.White,
                        start = Offset(
                            x = (screenWidthDp * density - startLandmark.position.x * scaleFactor) + offsetXDp * density,
                            y = (startLandmark.position.y * scaleFactor) - 15,
                        ),
                        end = Offset(
                            x = (screenWidthDp * density - endLandmark.position.x * scaleFactor) + offsetXDp * density,
                            y = (endLandmark.position.y * scaleFactor) - 15,
                        ),
                        strokeWidth = 10f,
                    )
                }

                // Draw all landmarks
                for (landmark in pose.allPoseLandmarks) {
                    val landmarkColor = when {
                        PoseConstants.LEFT_SIDE_LANDMARKS.contains(landmark.landmarkType) -> Color.Cyan
                        PoseConstants.RIGHT_SIDE_LANDMARKS.contains(landmark.landmarkType) -> {
                            Color(255, 175, 0) // Orange
                        }
                        PoseConstants.MIDDLE_SIDE_LANDMARKS.contains(landmark.landmarkType) -> Color.Cyan
                        else -> null
                    }
                    landmarkColor?.let {
                        // Landmark Background
                        drawCircle(
                            color = Color.White,
                            center = Offset(
                                x = ((screenWidthDp * density - landmark.position.x * scaleFactor) + offsetXDp * density),
                                y = ((landmark.position.y * scaleFactor) - 15),
                            ),
                            radius = 25f,
                        )
                        // Front Landmark
                        drawCircle(
                            color = it,
                            center = Offset(
                                x = ((screenWidthDp * density - landmark.position.x * scaleFactor) + offsetXDp * density),
                                y = ((landmark.position.y * scaleFactor) - 15),
                            ),
                            radius = 20f,
                        )
                    }
                }
            }

            // todo delete for debug
            Column {
                val leftElbowJoint = JointInfo.LeftElbowJoint
                val firstLandmark = pose.getPoseLandmark(leftElbowJoint.firstLandmark)
                val midLandmark = pose.getPoseLandmark(leftElbowJoint.midLandmark)
                val lastPoint = leftElbowJoint.lastLandmark?.let { pose.getPoseLandmark(it) }
                Log.d("First", firstLandmark.toString())
                Log.d("Mid", midLandmark.toString())
                Log.d("Last", lastPoint.toString())
                if (firstLandmark != null && midLandmark != null && lastPoint != null) {
                    Text(
                        text = AngleCalculator.getAngle(
                            firstPoint = firstLandmark,
                            midPoint = midLandmark,
                            lastPoint = lastPoint,
                        ).toString(),
                        color = Color.Blue,
                        style = MaterialTheme.typography.h3,
                    )
                    Log.d(
                        "Elbow",
                        AngleCalculator.getAngle(
                            firstPoint = firstLandmark,
                            midPoint = midLandmark,
                            lastPoint = lastPoint,
                        ).toString()
                    )
                }
            }
        }
    }
}