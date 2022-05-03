package com.jp_funda.boxiful.views.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.ui.theme.BlackAlpha80
import com.jp_funda.boxiful.ui.theme.Yellow500
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
        contentDescription = "", // TODO
        modifier = Modifier.fillMaxSize(),
    )

    // Foreground contents
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 50.dp, vertical = 100.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(BlackAlpha80)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = stringResource(id = R.string.login_en),
                    color = Color.White,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.ExtraBold,
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextField(value = "", onValueChange = {}, placeholder = { Text(text = "メールアドレス") })
                Spacer(modifier = Modifier.height(10.dp))
                TextField(value = "", onValueChange = {}, placeholder = { Text(text = "パスワード") })
                Spacer(modifier = Modifier.height(10.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Yellow500),
                    ) {
                        Text(text = stringResource(id = R.string.login))
                    }
                }
            }
        }
    }
}