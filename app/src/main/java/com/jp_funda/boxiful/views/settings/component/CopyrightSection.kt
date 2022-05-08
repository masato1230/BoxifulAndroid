package com.jp_funda.boxiful.views.settings.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.ui.theme.Yellow500

@Composable
fun CopyrightSection() {
    // Copy Light
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.settings_app_description),
            color = Color.White,
            fontSize = MaterialTheme.typography.subtitle2.fontSize * 0.8,
            modifier = Modifier.alpha(0.6f)
        )

        Divider(
            modifier = Modifier
                .padding(10.dp)
                .width(200.dp)
        )

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(Yellow500)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_service_thumbnail),
                contentDescription = stringResource(id = R.string.desc_app_icon),
                modifier = Modifier
                    .padding(10.dp)
                    .size(60.dp)
                    .alpha(0.6f),
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(id = R.string.settings_copyright),
            color = Color.White,
            fontSize = MaterialTheme.typography.subtitle2.fontSize * 0.8,
            modifier = Modifier.alpha(0.6f)
        )
    }
}