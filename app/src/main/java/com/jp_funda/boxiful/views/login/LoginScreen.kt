package com.jp_funda.boxiful.views.login

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.models.NetworkStatus
import com.jp_funda.boxiful.navigation.NavigationRoutes
import com.jp_funda.boxiful.ui.theme.*
import com.jp_funda.boxiful.views.components.ConfirmDialog
import com.jp_funda.boxiful.views.components.LoadingDialog

@Composable
fun LoginScreen(navController: NavController) {
    val viewModel = hiltViewModel<LoginViewModel>()
    val networkStatus = viewModel.networkStatus.observeAsState()
    if (networkStatus.value is NetworkStatus.Loading) {
        // Show loading dialog
        LoadingDialog(indicatorColor = Green500)
    } else if (networkStatus.value is NetworkStatus.Success) {
        // Show login success dialog
        ConfirmDialog(
            title = stringResource(id = R.string.auth_success),
            isShowDialog = remember { mutableStateOf(true) }
        ) {
            navController.popBackStack(
                NavigationRoutes.HOME,
                inclusive = false
            )
        }
    }

    Scaffold {
        Background()
        LoginMainContent(
            modifier = Modifier.padding(it),
            navController = navController,
            networkStatus = networkStatus.value!!,
        )
    }
}

@Composable
fun LoginMainContent(
    modifier: Modifier = Modifier,
    navController: NavController,
    networkStatus: NetworkStatus
) {
    val viewModel = hiltViewModel<LoginViewModel>()
    val context = LocalContext.current

    Column(
        modifier = modifier
            .padding(15.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(1f))

        // Thumbnail
        Card(backgroundColor = Yellow500) {
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
                withStyle(style = SpanStyle(color = Color.White)) {
                    append("ful")
                }
            },
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.ExtraBold,
        )
        Spacer(modifier = Modifier.height(30.dp))

        // Error message
        if (networkStatus is NetworkStatus.Error) {
            Text(
                text = stringResource(id = networkStatus.errorRes),
                color = Red900,
                modifier = Modifier
                    .padding(10.dp)
                    .background(Color.White.copy(alpha = 0.5f)),
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(10.dp))
        }

        // TextField for mail address
        val email = viewModel.email.observeAsState()
        TextField(
            value = email.value ?: "",
            onValueChange = { viewModel.setEmail(it) },
            placeholder = { Text(text = stringResource(id = R.string.auth_email)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            colors = TextFieldDefaults.textFieldColors(textColor = Color.White),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = stringResource(id = R.string.desc_icon),
                    tint = Color.White,
                    modifier = Modifier.padding(start = 15.dp, end = 10.dp),
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
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
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = stringResource(id = R.string.desc_icon),
                    tint = Color.White,
                    modifier = Modifier.padding(start = 15.dp, end = 10.dp),
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .border(width = 1.dp, color = Yellow500, shape = RoundedCornerShape(1000.dp))
                .clip(RoundedCornerShape(1000.dp))
        )
        Spacer(modifier = Modifier.height(5.dp))

        // Reset password link
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.White)) {
                    append("パスワードを忘れた方は")
                }
                withStyle(style = SpanStyle(color = Blue500)) {
                    append("こちら")
                }
            },
            textAlign = TextAlign.End,
            color = Color.Gray,
            style = MaterialTheme.typography.caption,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 10.dp)
                .clickable {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://masato.pythonanywhere.com/users/password_reset"),
                    )
                    context.startActivity(intent)
                },
        )
        Spacer(modifier = Modifier.height(40.dp))

        // Login Button
        Button(
            onClick = { viewModel.login() },
            colors = ButtonDefaults.buttonColors(backgroundColor = Pink400),
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
            Divider(modifier = modifier.weight(1f), color = Color.White)
            Text(
                text = stringResource(id = R.string.auth_or),
                color = Color.White,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            Divider(modifier = modifier.weight(1f), color = Color.White)
        }

        // Register Button
        Button(
            onClick = { viewModel.register() },
            colors = ButtonDefaults.buttonColors(backgroundColor = Green500),
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