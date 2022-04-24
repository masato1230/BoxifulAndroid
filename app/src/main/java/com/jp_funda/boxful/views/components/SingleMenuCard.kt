package com.jp_funda.boxful.views.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jp_funda.boxful.R
import com.jp_funda.boxful.models.SingleMenu
import com.jp_funda.boxful.ui.theme.Yellow500

@Composable
fun SingleMenuCard(menu: SingleMenu, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .heightIn(min = 250.dp)
            .width(200.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = menu.getThumbnail()),
                contentDescription = stringResource(id = R.string.menu),
                modifier = Modifier
                    .background(Yellow500)
                    .height(120.dp)
                    .fillMaxWidth(),
            )
            Text(
                text = stringResource(id = menu.titleRes),
                modifier = Modifier.padding(10.dp),
                style = MaterialTheme.typography.button,
                fontWeight = FontWeight.Bold,
            )

            Text(
                text = stringResource(id = menu.descriptionRes),
                modifier = Modifier.padding(horizontal = 5.dp),
                style = MaterialTheme.typography.body2,
                color = Color.LightGray,
                fontFamily = FontFamily.Serif,
            )
        }
    }
}