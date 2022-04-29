package com.jp_funda.boxiful.views.training

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.jp_funda.boxiful.models.Instruction
import com.jp_funda.boxiful.models.SingleMenu
import com.jp_funda.boxiful.navigation.NavigationRoutes
import com.jp_funda.boxiful.views.components.RequestCameraPermission
import com.jp_funda.boxiful.views.components.pose_preview.PosePreview
import com.jp_funda.boxiful.views.training.component.BottomInstructionOverlay
import com.jp_funda.boxiful.views.training.component.UpperInstructionOverlay

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalPermissionsApi
@Composable
fun TrainingScreen(navController: NavController, menu: SingleMenu) {
    // Set menu to viewModel
    hiltViewModel<TrainingViewModel>().setSingleMenu(menu)

    Scaffold {
        TrainingMainContent(navController = navController)
    }
}

/**
 * TrainingMainContent.
 */
@ExperimentalPermissionsApi
@Composable
fun TrainingMainContent(navController: NavController) {
    val viewModel = hiltViewModel<TrainingViewModel>()

    RequestCameraPermission(
        navController = navController,
    ) {
        PosePreview(poseObservers = listOf(viewModel))

        // Show training contents
        val observedInstructionIndex = viewModel.instructionIndex.observeAsState()
        observedInstructionIndex.value?.let { index ->
            if (index >= viewModel.getInstructions().size) {
                // Navigate to result screen
                navController.navigate(NavigationRoutes.RESULT) { popUpTo(NavigationRoutes.HOME) }
            } else {
                // Instruction overlay
                InstructionOverlay(
                    title = stringResource(viewModel.getSingleMenu().titleRes),
                    instructionIndex = index,
                    instructions = viewModel.getInstructions(),
                )
            }
        }
    }
}

/**
 * Instruction overlay contents.
 */
@Composable
fun InstructionOverlay(title: String, instructionIndex: Int, instructions: List<Instruction>) {
    val currentInstruction = instructions[instructionIndex]

    Column(modifier = Modifier.fillMaxSize()) {
        // Upper overlay
        UpperInstructionOverlay(title, instructionIndex, instructions)

        Spacer(modifier = Modifier.weight(1f))

        // Bottom overlay
        BottomInstructionOverlay(currentInstruction)
    }
}