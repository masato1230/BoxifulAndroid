package com.jp_funda.boxful.views.home

import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.jp_funda.boxful.R
import com.jp_funda.boxful.ui.theme.Gray900
import com.jp_funda.boxful.ui.theme.Yellow500

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
) {
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
        it
        Text(text = viewModel.title)
    }
}