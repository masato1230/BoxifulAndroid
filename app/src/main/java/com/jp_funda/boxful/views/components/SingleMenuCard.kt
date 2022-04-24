package com.jp_funda.boxful.views.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.jp_funda.boxful.R
import com.jp_funda.boxful.models.SingleMenu
import com.jp_funda.boxful.ui.theme.Yellow500

@Composable
fun SingleMenuCard(menu: SingleMenu, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column {
            Image(
                painter = painterResource(id = menu.getThumbnail()),
                contentDescription = stringResource(id = R.string.menu),
                Modifier.background(Yellow500)
            )
            Text(text = stringResource(id = menu.titleRes))
            Text(text = stringResource(id = menu.descriptionRes))
        }
    }
}