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

        Log.d(
            "x", (pose.getPoseLandmark(PoseLandmark.NOSE)?.position3D?.x?.toInt()
                ?: 0).toString()
        )
//        Log.d(
//            "y", (pose.getPoseLandmark(PoseLandmark.RIGHT_ELBOW)?.position?.y?.toInt()
//                ?: 0).toString()
//        )

        val posX = pose.getPoseLandmark(PoseLandmark.NOSE)?.position?.x ?: 0f
        val posY = pose.getPoseLandmark(PoseLandmark.NOSE)?.position?.y ?: 0f

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(modifier = Modifier
                .offset {
                    IntOffset(
                        x = (screenWidthDp * density - posX).toInt(),
                        y = posY.toInt(),
                    )
                }
                .size(50.dp)
                .clip(CircleShape)
                .background(Color.Green)
            )
        }
    }
}