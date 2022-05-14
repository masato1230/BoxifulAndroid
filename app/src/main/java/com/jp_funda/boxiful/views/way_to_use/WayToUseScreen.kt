package com.jp_funda.boxiful.views.way_to_use

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.ui.theme.Background
import com.jp_funda.boxiful.views.components.header.Header
import com.jp_funda.boxiful.views.way_to_use.component.WayToUseCard

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WayToUseScreen(navController: NavController) {
    Scaffold(topBar = { Header(navController) }) {
        Background()
        WayToUseMainContent(navController)
    }
}

@Composable
fun WayToUseMainContent(navController: NavController) {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Screen Title
        Text(
            text = stringResource(id = R.string.way_to_use),
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.ExtraBold,
            color = Color.White,
        )
        Spacer(modifier = Modifier.height(20.dp))

        // Lists
        WayToUsePage.values().forEach { pageInfo ->
            WayToUseCard(
                title = stringResource(id = pageInfo.titleRes),
                description = stringResource(id = pageInfo.descriptionRes),
                thumbnail = painterResource(id = pageInfo.thumbnail),
            )
            Spacer(modifier = Modifier.height(15.dp))
        }

        Spacer(modifier = Modifier.height(70.dp))
    }
}