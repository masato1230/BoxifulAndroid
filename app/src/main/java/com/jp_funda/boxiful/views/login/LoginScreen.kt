package com.jp_funda.boxiful.views.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.models.NetworkStatus
import com.jp_funda.boxiful.ui.theme.Yellow500
import com.jp_funda.boxiful.views.components.LoadingDialog

@Composable
fun LoginScreen(navController: NavController) {
    val viewModel = hiltViewModel<LoginViewModel>()
    val networkStatus = viewModel.networkStatus.observeAsState()
    if (networkStatus.value is NetworkStatus.Loading) {
        LoadingDialog()
    }

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
        Spacer(modifier = Modifier.weight(1f))

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

        // TextField for mail address
        val email = viewModel.email.observeAsState()
        TextField(
            value = email.value ?: "",
            onValueChange = { viewModel.setEmail(it) },
            placeholder = { Text(text = stringResource(id = R.string.auth_email)) },
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
        val password = viewModel.password.observeAsState()
        TextField(
            value = password.value ?: "",
            onValueChange = { viewModel.setPassword(it) },
            placeholder = { Text(text = stringResource(id = R.string.auth_password)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
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
        Spacer(modifier = Modifier.height(50.dp))

        // Login Button
        Button(
            onClick = { viewModel.login() },
            colors = ButtonDefaults.buttonColors(backgroundColor = Yellow500),
            shape = RoundedCornerShape(1000.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
        ) {
            Text(
                text = stringResource(id = R.string.auth_login),
                color = Color.White,
                fontWeight = FontWeight.Bold,
            )
        }

        // Divider
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Divider(modifier = modifier.weight(1f), color = Color.Gray)
            Text(text = stringResource(id = R.string.auth_or), modifier = Modifier.padding(horizontal = 10.dp))
            Divider(modifier = modifier.weight(1f), color = Color.Gray)
        }

        // Register Button
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(backgroundColor = Yellow500),
            shape = RoundedCornerShape(1000.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
        ) {
            Text(
                text = stringResource(id = R.string.auth_sign_up),
                color = Color.White,
                fontWeight = FontWeight.Bold,
            )
        }
        Spacer(modifier = Modifier.height(70.dp))
    }
}