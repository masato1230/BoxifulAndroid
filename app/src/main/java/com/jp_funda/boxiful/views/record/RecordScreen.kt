package com.jp_funda.boxiful.views.record

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.navigation.NavigationRoutes
import com.jp_funda.boxiful.ui.theme.Green500
import com.jp_funda.boxiful.ui.theme.Yellow500
import com.jp_funda.boxiful.views.components.header.Header

@Composable
fun RecordScreen(navController: NavController) {
    Scaffold(topBar = { Header(navController) }) {
        RecordMainContent(modifier = Modifier.padding(it), navController = navController)
    }
}

@Composable
fun RecordMainContent(modifier: Modifier = Modifier, navController: NavController) {
    val viewModel = hiltViewModel<RecordViewModel>()
    if (viewModel.isLoggedIn) {
        // If logged in, then fetch training results
        viewModel.getTrainingResults()
    } else {
        NotLoggedInContent(navController)
    }
}

@Composable
fun NotLoggedInContent(navController: NavController) {
    Column(modifier = Modifier.padding(30.dp)) {
        Image(
            painter = painterResource(id = R.drawable.ic_nothing),
            contentDescription = stringResource(id = R.string.desc_icon),
            modifier = Modifier.padding(50.dp),
        )
        Text(text = stringResource(id = R.string.record_login_needed_to_recording))
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(backgroundColor = Green500),
                modifier = Modifier.weight(0.5f),
            ) {
                Text(text = stringResource(id = R.string.permission_back_to_home))
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(
                onClick = { navController.navigate(NavigationRoutes.LOGIN) },
                colors = ButtonDefaults.buttonColors(backgroundColor = Yellow500),
                modifier = Modifier.weight(0.5f),
            ) {
                Text(text = stringResource(id = R.string.record_login))
            }
        }
    }
}