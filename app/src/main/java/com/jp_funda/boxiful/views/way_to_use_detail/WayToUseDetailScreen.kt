package com.jp_funda.boxiful.views.way_to_use_detail

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.ui.theme.Background
import com.jp_funda.boxiful.ui.theme.Green500
import com.jp_funda.boxiful.views.components.header.Header
import com.jp_funda.boxiful.views.way_to_use.WayToUsePage
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WayToUseDetailScreen(navController: NavController, page: WayToUsePage) {
    Scaffold(topBar = { Header(navController) }) {
        Background()
        WayToUseDetailMainContent(navController, page)
    }
}

@ExperimentalPagerApi
@Composable
fun WayToUseDetailMainContent(navController: NavController, page: WayToUsePage) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    
    Column {
        // Pager
        HorizontalPager(
            count = 5, // TODO Change
            state = pagerState,
            // Add 32.dp horizontal padding to 'center' the pages
            contentPadding = PaddingValues(horizontal = 32.dp),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // TODO something
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Indicator
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.1f),
            horizontalArrangement = Arrangement.Center,
        ) {
            HorizontalPagerIndicator(
                pagerState = pagerState,
                activeColor = Green500,
                inactiveColor = Color.DarkGray,
                indicatorHeight = 5.dp,
                indicatorWidth = 24.dp,
                spacing = 10.dp,
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Next or Start button
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .widthIn(500.dp)
                .height(50.dp)
                .clip(RoundedCornerShape(1000.dp))
                .background(
                    if (pagerState.currentPage + 1 < pagerState.pageCount) {
                        Color.White
                    } else {
                        Green500
                    }
                )
                .clickable {
                    if (pagerState.currentPage + 1 < pagerState.pageCount) {
                        scope.launch {
                            pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                        }
                    } else {
                        navController.popBackStack()
                    }
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (pagerState.currentPage + 1 < pagerState.pageCount) {
                Text(
                    text = stringResource(id = R.string.next),
                    color = Color.Black,
                    style = MaterialTheme.typography.h6,
                )
            } else {
                Text(
                    text = stringResource(id = R.string.way_to_use_back_to_list),
                    color = Color.White,
                    style = MaterialTheme.typography.h6,
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}