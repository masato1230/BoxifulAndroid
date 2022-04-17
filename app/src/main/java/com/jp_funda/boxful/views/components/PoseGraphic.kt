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
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.mlkit.vision.pose.PoseLandmark
import com.jp_funda.boxful.views.PoseViewModel

@Composable
fun PoseGraphic(poseViewModel: PoseViewModel = viewModel()) {
    val observedPose by poseViewModel.pose.observeAsState()

    observedPose?.let { pose ->

        Log.d(
            "x", (pose.getPoseLandmark(PoseLandmark.NOSE)?.position3D?.x?.toInt()
                ?: 0).toString()
        )
//        Log.d(
//            "y", (pose.getPoseLandmark(PoseLandmark.RIGHT_ELBOW)?.position?.y?.toInt()
//                ?: 0).toString()
//        )

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(modifier = Modifier
                .offset {
                    IntOffset(
                        x = pose.getPoseLandmark(PoseLandmark.RIGHT_ELBOW)?.position3D?.x?.toInt()?.times(10)
                            ?: 0,
                        y = pose.getPoseLandmark(PoseLandmark.RIGHT_ELBOW)?.position?.y?.toInt()
                            ?: 0,
                    )
                }
                .size(50.dp)
                .clip(CircleShape)
                .background(Color.Green)
            )
        }
    }
}