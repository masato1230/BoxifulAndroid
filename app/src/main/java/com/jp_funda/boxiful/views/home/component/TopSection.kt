package com.jp_funda.boxiful.views.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jp_funda.boxiful.R

@Composable
fun TopSection() {
    Column {
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
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
            modifier = Modifier
                .padding(start = 20.dp, top = 15.dp, end = 15.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h5,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.ExtraBold,
            color = Color.White,
        )
        Text(
            text = stringResource(id = R.string.home_service_description),
            modifier = Modifier.padding(start = 20.dp, top = 10.dp, end = 20.dp),
            fontFamily = FontFamily.Serif,
            color = Color.White,
        )
    }
}