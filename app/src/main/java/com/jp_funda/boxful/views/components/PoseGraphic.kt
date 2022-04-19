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
import com.jp_funda.boxful.views.PoseViewModel

@Composable
fun PoseGraphic(poseViewModel: PoseViewModel = viewModel()) {
    val observedPose by poseViewModel.pose.observeAsState()

    val screenWidthDp = LocalConfiguration.current.screenWidthDp
    val screenHeightDp = LocalConfiguration.current.screenHeightDp

    observedPose?.let { pose ->
        // Draw all landmarks
        with(LocalDensity.current) {
            val scaleFactor =
                (screenHeightDp * density) / poseViewModel.imageAnalysisResolution.height
            val offsetXDp =
                (screenHeightDp * poseViewModel.imageAnalysisResolution.width / poseViewModel.imageAnalysisResolution.height - screenWidthDp) / 2 - 10

            Canvas(modifier = Modifier.fillMaxSize()) {
                for (landmark in pose.allPoseLandmarks) {
                    drawCircle(
                        color = Color.Cyan,
                        center = Offset(
                            x = ((screenWidthDp * density - landmark.position.x * scaleFactor) + offsetXDp * density),
                            y = ((landmark.position.y * scaleFactor) - 20),
                        ),
                        radius = 20f,
                    )
                }
            }
        }
    }
}