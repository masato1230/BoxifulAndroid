package com.jp_funda.boxful.views.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jp_funda.boxful.R
import com.jp_funda.boxful.ui.theme.Gray900
import com.jp_funda.boxful.ui.theme.Yellow500

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

    Column(modifier = modifier) {
        Text(viewModel.title)
        // Top Section
        TopSection()

        // Dashboard Section for logged in user
        DashboardSection()

        // Menus List Section for logged in users
        MenuListSection()
    }
}

/** TopSection. */
@Composable
fun TopSection() {
    Text(text = "Top")
}

/** Dashboard Section for logged in user.  */
@Composable
fun DashboardSection() {
    Text(text = "Dashboard")
}

@Composable
fun MenuListSection() {
    Column {
        Text(text = stringResource(id = R.string.home_menu_list))
        Row {
            // TODO menu card
        }
    }
}