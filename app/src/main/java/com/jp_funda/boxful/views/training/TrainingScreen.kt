package com.jp_funda.boxful.views.training

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.jp_funda.boxful.models.SingleMenu
import com.jp_funda.boxful.views.components.RequestCameraPermission
import com.jp_funda.boxful.views.components.pose.CameraPreview
import com.jp_funda.boxful.views.components.pose.PoseGraphic

@ExperimentalPermissionsApi
@Composable
fun TrainingScreen(navController: NavController, menu: SingleMenu) {
    // Set menu to viewModel
    hiltViewModel<TrainingViewModel>().setSingleMenu(menu)

    Scaffold {
        TrainingMainContent(modifier = Modifier.padding(it), navController = navController)
    }
}

/**
 * TrainingMainContent.
 */
@ExperimentalPermissionsApi
@Composable
fun TrainingMainContent(modifier: Modifier = Modifier, navController: NavController) {
    val viewModel = hiltViewModel<TrainingViewModel>()
    Log.d("Single Menu", viewModel.getSingleMenu().name)

    RequestCameraPermission(
        navController = navController,
    ) {
        CameraPreview()
        PoseGraphic()
    }

    val observedInstructionIndex = viewModel.instructionIndex.observeAsState()
}