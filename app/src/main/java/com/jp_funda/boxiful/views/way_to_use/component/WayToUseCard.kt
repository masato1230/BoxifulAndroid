package com.jp_funda.boxiful.views.way_to_use.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.navigation.NavigationRoutes
import com.jp_funda.boxiful.views.way_to_use.WayToUsePage

@Composable
fun WayToUseCard(page: WayToUsePage, navController: NavController) {
    Card(
        modifier = Modifier
            .height(150.dp)
            .clickable {
                navController.navigate("${NavigationRoutes.WAY_TO_USE_DETAIL}/${page.name}")
            },
        backgroundColor = Color.White
    ) {
        Row(
            modifier = Modifier.padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier
                    .weight(0.3f)
                    .heightIn(max = 100.dp),
                painter = painterResource(id = page.thumbnail),
                contentDescription = stringResource(id = R.string.desc_icon),
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier.weight(0.7f)
            ) {
                Text(
                    text = stringResource(id = page.titleRes),
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.ExtraBold,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = stringResource(id = page.descriptionRes))
            }
        }
    }
}