package com.jp_funda.boxiful.views.intro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.ui.theme.Background
import com.jp_funda.boxiful.ui.theme.BoxifulTheme
import com.jp_funda.boxiful.ui.theme.Green500
import com.jp_funda.boxiful.views.way_to_use.WayToUsePage
import com.jp_funda.boxiful.views.way_to_use_detail.WayToUseDetailPage
import kotlinx.coroutines.launch

@ExperimentalPagerApi
class IntroActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BoxifulTheme {
                Background()
                IntroMainContent()
            }
        }
    }

    @Composable
    fun IntroMainContent() {
        val pagerState = rememberPagerState()
        val scope = rememberCoroutineScope()
        val getStartedPages =
            WayToUseDetailPage.getDetailPages(wayToUsePage = WayToUsePage.GetStarted)

        Column(modifier = Modifier.padding(20.dp)) {
            // Screen Title
            Text(
                text = stringResource(id = WayToUsePage.GetStarted.titleRes),
                color = Color.White,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Pager
            HorizontalPager(
                count = getStartedPages.size,
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f)
            ) { pageIndex ->
                val detailPage = getStartedPages[pageIndex]
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = painterResource(id = detailPage.thumbnailRes),
                        contentDescription = stringResource(id = R.string.desc_icon),
                        modifier = Modifier.height(180.dp)
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "${pageIndex + 1}. " + stringResource(id = detailPage.titleRes),
                        color = Color.White,
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.h5,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = stringResource(id = detailPage.contentRes),
                        color = Color.Black,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                    )
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
                            finish()
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
                        text = stringResource(id = R.string.start),
                        color = Color.White,
                        style = MaterialTheme.typography.h6,
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}