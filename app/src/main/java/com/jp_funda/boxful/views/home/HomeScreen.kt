package com.jp_funda.boxful.views.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jp_funda.boxful.R
import com.jp_funda.boxful.models.SingleMenu
import com.jp_funda.boxful.ui.theme.Gray900
import com.jp_funda.boxful.ui.theme.Yellow500
import com.jp_funda.boxful.views.components.SingleMenuCard

@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        buildAnnotatedString {
                            append("Boxi")
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
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.ic_boxiful),
                            contentDescription = "Service icon",
                            tint = Yellow500,
                        )
                    }
                },
                backgroundColor = Gray900,
            )
        }
    ) {
        HomeMainContent(modifier = Modifier.padding(it))
    }
}

/**
 * HomeMainContent.
 * This contains
 * ・Top Section
 * ・Dashboard Section for logged in user
 * ・Menu List Section
 */
@Composable
fun HomeMainContent(modifier: Modifier = Modifier) {
    val viewModel: HomeViewModel = hiltViewModel()

    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        // Top Section
        TopSection()

        // Dashboard Section for logged in user
        if (viewModel.isLoggedIn) {
            Spacer(modifier = Modifier.height(20.dp))
            DashboardSection()
        }

        // Menus List Section for logged in users
        Spacer(modifier = Modifier.height(20.dp))
        MenuListSection()
    }
}

/** TopSection. */
@Composable
fun TopSection() {
    // TODO add brand thumbnail image
    Column {
        Box(
            modifier = Modifier
                .background(Yellow500)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_service_thumbnail),
                contentDescription = "Service thumbnail",
                modifier = Modifier.heightIn(max = 300.dp)
            )
        }
        Text(
            text = stringResource(id = R.string.home_service_slogan),
            modifier = Modifier.padding(start = 20.dp, top = 15.dp, end = 15.dp),
            style = MaterialTheme.typography.h6,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.ExtraBold,
        )
        Text(
            text = stringResource(id = R.string.home_service_description),
            modifier = Modifier.padding(start = 20.dp, top = 10.dp, end = 20.dp),
            fontFamily = FontFamily.Serif,
            color = Color.LightGray,
        )
    }
}

/** Dashboard Section for logged in user.  */
@Composable
fun DashboardSection() {
    // TODO add something to show
    Text(
        text = "Dashboard",
        modifier = Modifier.padding(start = 20.dp),
    )
}

@Composable
fun MenuListSection() {
    Column {
        // Section Title
        Text(
            text = stringResource(id = R.string.home_menu_list),
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 20.dp),
        )

        Spacer(modifier = Modifier.height(10.dp))

        // List
        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState()),
        ) {
            Spacer(modifier = Modifier.width(20.dp))

            // Single menu cards
            for (menu in SingleMenu.values()) {
                SingleMenuCard(menu = menu)
                Spacer(modifier = Modifier.width(20.dp))
            }
        }
        Spacer(modifier = Modifier.height(100.dp))
    }
}