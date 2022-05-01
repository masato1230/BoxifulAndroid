package com.jp_funda.boxiful.views.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.ui.theme.Gray900
import com.jp_funda.boxiful.ui.theme.Yellow500

@Composable
fun Header() {
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