package com.jp_funda.boxiful.views.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.models.SingleMenu
import com.jp_funda.boxiful.navigation.NavigationRoutes
import com.jp_funda.boxiful.ui.theme.Yellow500
import com.jp_funda.boxiful.views.components.header.Header
import com.jp_funda.boxiful.views.components.SingleMenuCard
import com.jp_funda.boxiful.views.components.TrainingStartDailog

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(topBar = { Header(navController) }) {
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

        // Dashboard Section for logged in user
        if (viewModel.isLoggedIn) {
            Spacer(modifier = Modifier.height(20.dp))
            DashboardSection()
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
        Box(
            modifier = Modifier
                .background(Yellow500)
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
            modifier = Modifier.padding(start = 20.dp, top = 15.dp, end = 15.dp),
            style = MaterialTheme.typography.h6,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.ExtraBold,
        )
        Text(
            text = stringResource(id = R.string.home_service_description),
            modifier = Modifier.padding(start = 20.dp, top = 10.dp, end = 20.dp),
            fontFamily = FontFamily.Serif,
            color = Color.LightGray,
        )
    }
}

/** Dashboard Section for logged in user.  */
@Composable
fun DashboardSection() {
    // TODO add something to show
    Text(
        text = "Dashboard",
        modifier = Modifier.padding(start = 20.dp),
    )
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
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold,
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