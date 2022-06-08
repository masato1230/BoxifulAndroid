package com.jp_funda.boxiful.views.home.component

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.models.SeriesMenu
import com.jp_funda.boxiful.views.components.SeriesMenuCard
import com.jp_funda.boxiful.views.components.TrainingStartDailog
import com.jp_funda.boxiful.views.home.HomeViewModel

@Composable
fun SeriesMenusList(navController: NavController) {
    val viewModel: HomeViewModel = hiltViewModel()
    // Camera open dialog
    val isShowCameraOpenDialog = remember { mutableStateOf(false) }
    TrainingStartDailog(isShowCameraOpenDialog) {
        // TODO navigate to seriesMenuTrainingScreen
    }

    // Menus
    Column {
        // Section Title
        Text(
            text = stringResource(id = R.string.home_series_menu_title),
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(start = 20.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        // Section Description
        Text(
            text = stringResource(id = R.string.home_series_menu_description),
            modifier = Modifier.padding(horizontal = 20.dp),
            style = MaterialTheme.typography.body2,
            fontFamily = FontFamily.Serif,
            color = Color.White,
        )

        Spacer(modifier = Modifier.height(10.dp))

        // List
        Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
            Spacer(modifier = Modifier.width(20.dp))

            // Single menu cards
            for (menu in SeriesMenu.values()) {
                SeriesMenuCard(menu = menu) {
                    viewModel.setSelectedSeriesMenu(menu)
                    isShowCameraOpenDialog.value = true
                }
                Spacer(modifier = Modifier.width(20.dp))
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}