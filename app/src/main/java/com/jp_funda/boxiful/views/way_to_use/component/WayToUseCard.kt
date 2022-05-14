package com.jp_funda.boxiful.views.way_to_use.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jp_funda.boxiful.R

@Composable
fun WayToUseCard(
    title: String,
    description: String,
    thumbnail: Painter,
) {
    Card(
        modifier = Modifier.height(80.dp),
        backgroundColor = Color.White
    ) {
        Row(
            modifier = Modifier.padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier.weight(0.3f),
                painter = thumbnail,
                contentDescription = stringResource(id = R.string.desc_icon),
            )
            Column(
                modifier = Modifier.weight(0.7f)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.ExtraBold,
                )
                Text(text = description)
            }
        }
    }
}

@Preview
@Composable
fun PreviewWayToUseCard() {
    WayToUseCard(
        title = "Get Started",
        description = "Boxifulの基本的な使い方",
        thumbnail = painterResource(id = R.drawable.ic_service_thumbnail),
    )
}