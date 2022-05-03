package com.jp_funda.boxiful.views.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.ui.theme.Yellow500

@Composable
fun LoginScreen(navController: NavController) {
    Scaffold {
        LoginMainContent(modifier = Modifier.padding(it), navController = navController)
    }
}

@Composable
fun LoginMainContent(modifier: Modifier = Modifier, navController: NavController) {
    val viewModel = hiltViewModel<LoginViewModel>()

    Column(
        modifier = modifier
            .padding(30.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(0.2f))

        // Thumbnail
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(Yellow500.copy(alpha = 0.8f))
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_service_thumbnail),
                contentDescription = stringResource(id = R.string.desc_icon),
                modifier = Modifier
                    .padding(10.dp)
                    .size(80.dp),
            )
        }
        Text(
            text = buildAnnotatedString {
                append("Boxi")
                withStyle(style = SpanStyle(color = Yellow500)) {
                    append("ful")
                }
            },
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.ExtraBold,
        )
        Spacer(modifier = Modifier.height(30.dp))

        // Title(Login)
        Text(
            text = stringResource(id = R.string.login),
            color = Color.White,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        // TextField for mail address
        TextField(
            value = "",
            onValueChange = {},
            placeholder = { Text(text = "メールアドレス") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = stringResource(id = R.string.desc_icon),
                    tint = Color.White,
                    modifier = Modifier.padding(start = 30.dp, end = 10.dp),
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .border(width = 1.dp, color = Yellow500, shape = RoundedCornerShape(1000.dp))
                .clip(RoundedCornerShape(1000.dp))
        )
        Spacer(modifier = Modifier.height(20.dp))

        // TextField for password
        TextField(
            value = "",
            onValueChange = {},
            placeholder = { Text(text = "パスワード") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = stringResource(id = R.string.desc_icon),
                    tint = Color.White,
                    modifier = Modifier.padding(start = 30.dp, end = 10.dp),
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .border(width = 1.dp, color = Yellow500, shape = RoundedCornerShape(1000.dp))
                .clip(RoundedCornerShape(1000.dp))
        )
        Spacer(modifier = Modifier.weight(0.2f))

        // Login Button
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Yellow500),
                shape = RoundedCornerShape(1000.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
            ) {
                Text(
                    text = stringResource(id = R.string.login),
                    color = Color.White,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                )
            }
        }

        Spacer(modifier = Modifier.weight(0.1f))
    }
}