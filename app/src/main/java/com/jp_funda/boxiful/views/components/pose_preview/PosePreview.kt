package com.jp_funda.boxiful.views.components.pose_preview

import androidx.compose.runtime.Composable

@Composable
fun PosePreview(poseObservers: List<PoseObserver>) {
    CameraPreview(poseObservers = poseObservers)
    PoseGraphic()
}