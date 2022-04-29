package com.jp_funda.boxful.views.training

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.jp_funda.boxful.models.Instruction
import com.jp_funda.boxful.models.SingleMenu
import com.jp_funda.boxful.ui.theme.BlackAlpha50
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

        // instruction overlays
        val observedInstructionIndex = viewModel.instructionIndex.observeAsState()
        observedInstructionIndex.value?.let {
            InstructionOverlay(instruction = viewModel.getInstruction(it))
        }
    }
}

/**
 * Instruction overlay contents.
 */
@Composable
fun InstructionOverlay(instruction: Instruction) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(BlackAlpha50)
            .padding(vertical = 30.dp)
    ) {
        Text(
            text = stringResource(id = instruction.titleRes),
            fontSize = MaterialTheme.typography.h4.fontSize * 1.2,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}