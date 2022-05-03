package com.jp_funda.boxiful.views.login

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jp_funda.boxiful.views.components.header.Header

@Composable
fun LoginScreen(navController: NavController) {
    Scaffold(topBar = { Header(navController) }) {
        LoginMainContent(modifier = Modifier.padding(it), navController = navController)
    }
}

@Composable
fun LoginMainContent(modifier: Modifier = Modifier, navController: NavController) {
    val viewModel = hiltViewModel<LoginViewModel>()
}