package com.jp_funda.boxiful.views.training

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.jp_funda.boxiful.models.Instruction
import com.jp_funda.boxiful.models.SingleMenu
import com.jp_funda.boxiful.views.components.RequestCameraPermission
import com.jp_funda.boxiful.views.components.pose.CameraPreview
import com.jp_funda.boxiful.views.components.pose.PoseGraphic
import com.jp_funda.boxiful.views.training.component.BottomInstructionOverlay
import com.jp_funda.boxiful.views.training.component.UpperInstructionOverlay

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

        // instruction overlays
        val observedInstructionIndex = viewModel.instructionIndex.observeAsState()
        observedInstructionIndex.value?.let {
            InstructionOverlay(instructionIndex = it, instructions = viewModel.getInstructions())
        }
    }
}

/**
 * Instruction overlay contents.
 */
@Composable
fun InstructionOverlay(instructionIndex: Int, instructions: List<Instruction>) {
    val currentInstruction = instructions[instructionIndex]

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
    ) {
        // Upper overlay
        UpperInstructionOverlay(instructionIndex, instructions)

        // Bottom overlay
        BottomInstructionOverlay(currentInstruction)
    }
}