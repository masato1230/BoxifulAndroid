package com.jp_funda.boxful.views.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.mlkit.vision.pose.PoseLandmark
import com.jp_funda.boxful.views.PoseViewModel

@Composable
fun PoseGraphic(poseViewModel: PoseViewModel = viewModel()) {
    val observedPose by poseViewModel.pose.observeAsState()

    observedPose?.let { pose ->
        Text(text = pose.getPoseLandmark(PoseLandmark.RIGHT_ELBOW)?.position?.x.toString())
    }
}