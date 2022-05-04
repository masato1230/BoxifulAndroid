package com.jp_funda.boxiful.views.my_page

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.ui.theme.Yellow500
import com.jp_funda.boxiful.views.components.header.Header

@Composable
fun MyPageScreen(navController: NavController) {
    Scaffold(topBar = { Header(navController) }) {
        MyPageMainContent(modifier = Modifier.padding(it), navController = navController)
    }
}

@Composable
fun MyPageMainContent(modifier: Modifier = Modifier, navController: NavController) {
    val viewModel = hiltViewModel<MyPageViewModel>()

    Column(modifier = modifier.padding(30.dp)) {
        Row {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = stringResource(id = R.string.desc_icon),
                tint = Yellow500,
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = viewModel.email)
        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {
                viewModel.logout()
                navController.popBackStack()
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Yellow500),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.auth_logout))
        }
    }
}