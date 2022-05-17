package com.jp_funda.boxiful.views.components.header

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.navigation.NavigationRoutes
import com.jp_funda.boxiful.ui.theme.Pink400
import com.jp_funda.boxiful.ui.theme.Yellow500

@Composable
fun Header(navController: NavController) {
    val viewModel = hiltViewModel<HeaderViewModel>()
    TopAppBar(
        title = {
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.White)) {
                        append("Boxi")
                    }
                    withStyle(style = SpanStyle(color = Yellow500)) {
                        append("ful")
                    }
                }
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = stringResource(id = R.string.kickboxing),
                style = MaterialTheme.typography.caption,
                fontFamily = FontFamily.Serif,
                color = Color.White,
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack(NavigationRoutes.HOME, false)
            }) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.ic_boxiful),
                    contentDescription = stringResource(id = R.string.desc_icon),
                    tint = Yellow500,
                )
            }
        },
        actions = {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = stringResource(id = R.string.desc_account_icon),
                modifier = Modifier
                    .size(32.dp)
                    .clickable {
                        if (viewModel.isLoggedIn) {
                            navController.navigate(NavigationRoutes.MY_PAGE)
                        } else {
                            navController.navigate(NavigationRoutes.LOGIN)
                        }
                    }
                    .clip(CircleShape)
                    .background(if (viewModel.isLoggedIn) Color.White else Color.Transparent),
                tint = if (viewModel.isLoggedIn) Yellow500 else Color.LightGray,
            )
            Spacer(modifier = Modifier.width(10.dp))
        },
        backgroundColor = Pink400,
    )
}