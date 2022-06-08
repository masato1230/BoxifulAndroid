package com.jp_funda.boxiful.views.series_training

import android.annotation.SuppressLint
import android.media.MediaPlayer
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.models.SeriesMenu
import com.jp_funda.boxiful.ui.theme.Background
import com.jp_funda.boxiful.views.MainViewModel
import com.jp_funda.boxiful.views.components.RequestCameraPermission

@ExperimentalPermissionsApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SeriesTrainingScreen(navController: NavController, menu: SeriesMenu, mainViewModel: MainViewModel) {
    // Set menu to viewModel
    hiltViewModel<SeriesTrainingViewModel>().setSeriesMenu(menu)

    Scaffold {
        Background()
        RequestCameraPermission(navController = navController) {
            SeriesTrainingMainContent(navController, mainViewModel)
        }
    }
}

/** Series TrainingMainContent. */
@Composable
fun SeriesTrainingMainContent(navController: NavController, mainViewModel: MainViewModel) {
    val viewModel = hiltViewModel<SeriesTrainingViewModel>()
    val context = LocalContext.current
    val greatSoundPlayer = remember { MediaPlayer.create(context, R.raw.great_punch) }
    val goodSoundPlayer = remember { MediaPlayer.create(context, R.raw.good_punch) }
    val missSoundPlayer = remember { MediaPlayer.create(context, R.raw.miss_punch) }
    val finishSoundPlayer = remember { MediaPlayer.create(context, R.raw.finish) }
}