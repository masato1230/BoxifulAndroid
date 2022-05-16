package com.jp_funda.boxiful.views.my_page

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.navigation.NavigationRoutes
import com.jp_funda.boxiful.ui.theme.Background
import com.jp_funda.boxiful.ui.theme.Red500
import com.jp_funda.boxiful.ui.theme.Yellow500
import com.jp_funda.boxiful.views.components.ConfirmDialog
import com.jp_funda.boxiful.views.components.header.Header

@Composable
fun MyPageScreen(navController: NavController) {
    Scaffold(topBar = { Header(navController) }) {
        Background()
        MyPageMainContent(modifier = Modifier.padding(it), navController = navController)
    }
}

@Composable
fun MyPageMainContent(modifier: Modifier = Modifier, navController: NavController) {
    val viewModel = hiltViewModel<MyPageViewModel>()
    // TODO Observe NetworkStatus for delete account

    // Logout finish dialog
    val isShowLoggedOutDialog = remember { mutableStateOf(false) }
    if (isShowLoggedOutDialog.value) {
        ConfirmDialog(
            title = stringResource(id = R.string.auth_logged_out),
            isShowDialog = isShowLoggedOutDialog,
        ) {
            navController.popBackStack(route = NavigationRoutes.HOME, inclusive = false)
        }
    }

    // Delete account confirm dialog
    val isShowDeleteAccountDialog = remember { mutableStateOf(false) }
    if (isShowDeleteAccountDialog.value) {
        ConfirmDialog(
            title = stringResource(id = R.string.auth_ask_delete_account),
            isShowDialog = isShowDeleteAccountDialog,
            onDismiss = {},
            isShowNegativeButton = true,
            onClickPositive = {
                viewModel.deleteAccount()
            },
        )
    }

    Column(modifier = modifier.padding(30.dp)) {
        Row {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = stringResource(id = R.string.desc_icon),
                tint = Yellow500,
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = viewModel.email, color = Color.White)
        }
        Spacer(modifier = Modifier.height(30.dp))
        // Button for logout
        Button(
            onClick = {
                isShowLoggedOutDialog.value = true
                viewModel.logout()
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Yellow500),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.auth_logout))
        }
        // Button for delete account
        Button(
            onClick = {
                isShowDeleteAccountDialog.value = true
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Red500),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.auth_delete_account), color = Color.White)
        }
    }
}