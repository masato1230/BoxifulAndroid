package com.jp_funda.boxiful.views.training

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.models.Instruction
import com.jp_funda.boxiful.models.SingleMenu
import com.jp_funda.boxiful.ui.theme.BlackAlpha50
import com.jp_funda.boxiful.views.components.RequestCameraPermission
import com.jp_funda.boxiful.views.components.pose.CameraPreview
import com.jp_funda.boxiful.views.components.pose.PoseGraphic

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
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
    ) {
//        Spacer(modifier = Modifier.weight(1f))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(BlackAlpha50)
                .padding(vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(id = instruction.titleRes),
                fontSize = MaterialTheme.typography.h4.fontSize * 1.2,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(10.dp))

            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = stringResource(id = R.string.desc_left),
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color.Red)
            )
        }
    }
}