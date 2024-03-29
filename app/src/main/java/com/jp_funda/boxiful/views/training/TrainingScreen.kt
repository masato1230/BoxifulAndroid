package com.jp_funda.boxiful.views.training

import android.annotation.SuppressLint
import android.media.MediaPlayer
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.jp_funda.boxiful.AppConst
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.models.NetworkStatus
import com.jp_funda.boxiful.models.SingleMenu
import com.jp_funda.boxiful.models.SingleMenuResult
import com.jp_funda.boxiful.navigation.NavigationRoutes
import com.jp_funda.boxiful.ui.theme.Background
import com.jp_funda.boxiful.views.MainViewModel
import com.jp_funda.boxiful.views.components.RequestCameraPermission
import com.jp_funda.boxiful.views.components.pose_preview.PosePreview
import com.jp_funda.boxiful.views.training.component.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalPermissionsApi
@Composable
fun TrainingScreen(navController: NavController, menu: SingleMenu, mainViewModel: MainViewModel) {
    // Set menu to viewModel
    hiltViewModel<TrainingViewModel>().setSingleMenu(menu)

    Scaffold {
        Background()
        TrainingMainContent(navController = navController, mainViewModel = mainViewModel)
    }
}

/**
 * TrainingMainContent.
 */
@ExperimentalPermissionsApi
@Composable
fun TrainingMainContent(navController: NavController, mainViewModel: MainViewModel) {
    val viewModel = hiltViewModel<TrainingViewModel>()
    val context = LocalContext.current
    val greatSoundPlayer = remember { MediaPlayer.create(context, R.raw.great_punch) }
    val goodSoundPlayer = remember { MediaPlayer.create(context, R.raw.good_punch) }
    val missSoundPlayer = remember { MediaPlayer.create(context, R.raw.miss_punch) }
    val finishSoundPlayer = remember { MediaPlayer.create(context, R.raw.finish) }

    RequestCameraPermission(
        navController = navController,
    ) {
        // Camera & Pose Preview
        PosePreview(poseObservers = listOf(viewModel))

        // ErrorDialog
        val networkStatus = viewModel.networkStatus.observeAsState()
        if (networkStatus.value is NetworkStatus.Error) {
            ErrorRegisteringResultDialog {
                navController.navigate(NavigationRoutes.RESULT) { popUpTo(NavigationRoutes.HOME) }
            }
        }

        // Show training contents
        val observedInstructionIndex = viewModel.instructionIndex.observeAsState()
        observedInstructionIndex.value?.let { index ->
            // Play soundEffect
            when (index) {
                0 -> {}
                in 1 until viewModel.getInstructions().size -> {
                    val previousScore = viewModel.getScores()[index - 1]
                    when {
                        previousScore > AppConst.MAX_GOOD_SCORE -> greatSoundPlayer.start()
                        previousScore < AppConst.MIN_GOOD_SCORE -> missSoundPlayer.start()
                        else -> goodSoundPlayer.start()
                    }
                }
                viewModel.getInstructions().size -> {
                    if (!finishSoundPlayer.isPlaying) finishSoundPlayer.start()
                }
            }

            // Overlays
            // When finish menu
            if (index >= viewModel.getInstructions().size) {
                // Pass data to MainViewModel
                mainViewModel.singleMenuScores = SingleMenuResult(
                    singleMenu = viewModel.getSingleMenu(),
                    scores = viewModel.getScores(),
                    instructions = viewModel.getInstructions()
                )
                // Show finish modal
                FinishModal()
                // Register training result to server
                viewModel.registerTrainingResult()
                // Navigate to result screen with delay
                val isAlreadyFiredNavigation = remember { mutableStateOf(false) }
                val composableScope = rememberCoroutineScope()
                if (!isAlreadyFiredNavigation.value) {
                    composableScope.launch {
                        delay(1500)
                        if (networkStatus.value is NetworkStatus.Success || networkStatus.value is NetworkStatus.Waiting) {
                            navController.navigate(NavigationRoutes.RESULT) {
                                popUpTo(NavigationRoutes.HOME)
                            }
                        }
                    }
                    isAlreadyFiredNavigation.value = true
                }
            } else { // Next instruction
                val overlayType = viewModel.overlayType.observeAsState()

                overlayType.value?.let { type ->
                    when (type) {
                        OverlayType.Countdown -> {
                            viewModel.startCountDown()
                            CountDownOverlay()
                        }
                        OverlayType.Instruction -> {
                            // Instruction overlay
                            InstructionOverlay(
                                title = stringResource(viewModel.getSingleMenu().titleRes),
                                instructionIndex = index,
                                instructions = viewModel.getInstructions(),
                            )
                        }
                        OverlayType.Warning -> {
                            WarningOverlay()
                        }
                    }
                }
            }
        }
    }
}