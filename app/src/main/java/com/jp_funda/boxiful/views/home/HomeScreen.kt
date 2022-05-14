package com.jp_funda.boxiful.views.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.models.SingleMenu
import com.jp_funda.boxiful.navigation.NavigationRoutes
import com.jp_funda.boxiful.ui.theme.Background
import com.jp_funda.boxiful.views.components.SingleMenuCard
import com.jp_funda.boxiful.views.components.TrainingStartDailog
import com.jp_funda.boxiful.views.components.header.Header
import com.jp_funda.boxiful.views.home.component.TodayStatsSection

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(topBar = { Header(navController) }) {
        Background()
        HomeMainContent(modifier = Modifier.padding(it), navController = navController)
    }
}

/**
 * HomeMainContent.
 * This contains
 * ・Top Section
 * ・Dashboard Section for logged in user
 * ・Menu List Section
 */
@Composable
fun HomeMainContent(modifier: Modifier = Modifier, navController: NavController) {
    val viewModel: HomeViewModel = hiltViewModel()

    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        // Top Section
        TopSection()

        // Today's　Stats Section for logged in user
        if (viewModel.isLoggedIn) {
            Spacer(modifier = Modifier.height(20.dp))
            TodayStatsSection(modifier = Modifier.padding(horizontal = 20.dp))
        }

        // Menus List Section for logged in users
        Spacer(modifier = Modifier.height(20.dp))
        MenuListSection(navController)
    }
}

/** TopSection. */
@Composable
fun TopSection() {
    Column {
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_service_thumbnail),
                contentDescription = "Service thumbnail",
                modifier = Modifier.heightIn(max = 300.dp)
            )
        }
        Text(
            text = stringResource(id = R.string.home_service_slogan),
            modifier = Modifier
                .padding(start = 20.dp, top = 15.dp, end = 15.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h5,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.ExtraBold,
            color = Color.White,
        )
        Text(
            text = stringResource(id = R.string.home_service_description),
            modifier = Modifier.padding(start = 20.dp, top = 10.dp, end = 20.dp),
            fontFamily = FontFamily.Serif,
            color = Color.White,
        )
    }
}

@Composable
fun MenuListSection(navController: NavController) {
    val viewModel: HomeViewModel = hiltViewModel()
    // Camera open dialog
    val isShowCameraOpenDialog = remember { mutableStateOf(false) }
    TrainingStartDailog(isShowCameraOpenDialog) {
        navController.navigate("${NavigationRoutes.TRAINING}/${viewModel.getSelectedMenu().name}")
    }

    // Section Contents
    Column {
        // Section Title
        Text(
            text = stringResource(id = R.string.home_menu_list),
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(start = 20.dp),
        )

        Spacer(modifier = Modifier.height(10.dp))

        // List
        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState()),
        ) {
            Spacer(modifier = Modifier.width(20.dp))

            // Single menu cards
            for (menu in SingleMenu.values()) {
                SingleMenuCard(menu = menu) {
                    viewModel.setSelectedMenu(menu)
                    isShowCameraOpenDialog.value = true
                }
                Spacer(modifier = Modifier.width(20.dp))
            }
        }
        Spacer(modifier = Modifier.height(100.dp))
    }
}