package com.jp_funda.boxful.views.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
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

        val posNoseX = pose.getPoseLandmark(PoseLandmark.NOSE)?.position?.x ?: 0f
        val posNoseY = pose.getPoseLandmark(PoseLandmark.NOSE)?.position?.y ?: 0f
        val posLeftEyeX = pose.getPoseLandmark(PoseLandmark.LEFT_EYE)?.position?.x ?: 0f
        val posLeftEyeY = pose.getPoseLandmark(PoseLandmark.LEFT_EYE)?.position?.y ?: 0f
        val posRightEyeX = pose.getPoseLandmark(PoseLandmark.RIGHT_EYE)?.position?.x ?: 0f
        val posRightEyeY = pose.getPoseLandmark(PoseLandmark.RIGHT_EYE)?.position?.y ?: 0f

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(modifier = Modifier
                .offset {
                    val analyzedImageScaleX =
                        poseViewModel.imageAnalysisResolution.width / (screenWidthDp * density)
                    val analyzedImageScaleY =
                        poseViewModel.imageAnalysisResolution.height / (screenHeightDp * density)

                    Log.d("Size after", (screenHeightDp * density).toString())
                    Log.d("Scale", "$analyzedImageScaleX ,$analyzedImageScaleY")
                    IntOffset(
                        x = (screenWidthDp * density - posNoseX).toInt(),
                        y = (posNoseY).toInt(),
                    )
                }
                .size(15.dp)
                .clip(CircleShape)
                .background(Color.Green)
            )

            Box(modifier = Modifier
                .offset {
                    val analyzedImageScaleX =
                        poseViewModel.imageAnalysisResolution.width / (screenWidthDp * density)
                    val analyzedImageScaleY =
                        poseViewModel.imageAnalysisResolution.height / (screenHeightDp * density)
                    Log.d("Size after", (screenHeightDp * density).toString())
                    IntOffset(
                        x = (screenWidthDp * density - posLeftEyeX).toInt(),
                        y = (posLeftEyeY).toInt(),
                    )
                }
                .size(15.dp)
                .clip(CircleShape)
                .background(Color.Green)
            )

            Box(modifier = Modifier
                .offset {
                    val analyzedImageScaleX =
                        poseViewModel.imageAnalysisResolution.width / (screenWidthDp * density)
                    val analyzedImageScaleY =
                        poseViewModel.imageAnalysisResolution.height / (screenHeightDp * density)
                    Log.d("Size after", (screenHeightDp * density).toString())
                    IntOffset(
                        x = (screenWidthDp * density - posRightEyeX).toInt(),
                        y = (posRightEyeY).toInt(),
                    )
                }
                .size(15.dp)
                .clip(CircleShape)
                .background(Color.Green)
            )
        }
    }
}