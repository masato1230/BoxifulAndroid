package com.jp_funda.boxiful.views.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.views.components.header.Header

@Composable
fun LoginScreen(navController: NavController) {
    Scaffold(topBar = { Header(navController) }, backgroundColor = Color.Transparent) {
        LoginMainContent(modifier = Modifier.padding(it), navController = navController)
    }
}

@Composable
fun LoginMainContent(modifier: Modifier = Modifier, navController: NavController) {
    val viewModel = hiltViewModel<LoginViewModel>()

    // Background image
    Image(
        painter = painterResource(id = R.drawable.bg_login),
        contentScale = ContentScale.FillHeight,
        contentDescription = "",
        modifier = Modifier.fillMaxSize(),
    )
}