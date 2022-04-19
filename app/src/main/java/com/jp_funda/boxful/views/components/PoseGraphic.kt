package com.jp_funda.boxful.views.components

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.mlkit.vision.pose.PoseLandmark
import com.jp_funda.boxful.utils.PoseConstants
import com.jp_funda.boxful.views.PoseViewModel

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
                // Draw all landmarks
                for (landmark in pose.allPoseLandmarks) {
                    drawCircle(
                        color = Color.Cyan,
                        center = Offset(
                            x = ((screenWidthDp * density - landmark.position.x * scaleFactor) + offsetXDp * density),
                            y = ((landmark.position.y * scaleFactor) - 15),
                        ),
                        radius = 20f,
                    )
                }

                // Draw all bones
                for (bonePair in PoseConstants.BONE_LANDMARK_SETS) {
                    val startLandmark = pose.getPoseLandmark(bonePair.first)
                    val endLandmark = pose.getPoseLandmark(bonePair.second)

                    if (startLandmark == null || endLandmark == null) continue
                    drawLine(
                        color = Color.LightGray,
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
            }
        }
    }
}