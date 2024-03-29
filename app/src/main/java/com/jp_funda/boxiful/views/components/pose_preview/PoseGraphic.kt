package com.jp_funda.boxiful.views.components.pose_preview

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.hilt.navigation.compose.hiltViewModel
import com.jp_funda.boxiful.utils.pose.PoseConstants

@Composable
fun PoseGraphic() {
    val poseGraphicViewModel = hiltViewModel<PoseGraphicViewModel>()
    val observedPose by poseGraphicViewModel.pose.observeAsState()

    val screenWidthDp = LocalConfiguration.current.screenWidthDp
    val screenHeightDp = LocalConfiguration.current.screenHeightDp

    observedPose?.let { pose ->
        with(LocalDensity.current) {
            val scaleFactor =
                (screenHeightDp * density) / poseGraphicViewModel.imageAnalysisResolution.height
            val offsetXDp =
                (screenHeightDp * poseGraphicViewModel.imageAnalysisResolution.width / poseGraphicViewModel.imageAnalysisResolution.height - screenWidthDp) / 2 - 10

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
        }
    }
}