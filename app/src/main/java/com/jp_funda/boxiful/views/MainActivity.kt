package com.jp_funda.boxiful.views

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.jp_funda.boxiful.models.SingleMenu
import com.jp_funda.boxiful.ui.theme.BoxfulTheme
import com.jp_funda.boxiful.views.components.pose_preview.CameraPreview
import com.jp_funda.boxiful.views.components.pose_preview.PoseGraphic
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagerApi
@ExperimentalAnimationApi
@ExperimentalPermissionsApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Refresh auth tokens
        viewModel.refreshAuthTokens()

        setContent {
            BoxfulTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen(viewModel)
                }
            }
        }
    }
}

@ExperimentalPermissionsApi
@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    menu: SingleMenu? = null
) { // TODO change second param
    Log.d("Single Menu", menu?.name.toString())
    val context = LocalContext.current
    CameraPreview(poseObservers = listOf())
    PoseGraphic()
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BoxfulTheme {
        Greeting("Android")
    }
}