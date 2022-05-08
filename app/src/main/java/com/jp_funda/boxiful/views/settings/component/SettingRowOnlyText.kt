package com.jp_funda.boxiful.views.settings.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jp_funda.boxiful.R

/** SettingRow with only text info */
@Composable
fun SettingRowOnlyText(
    icon: ImageVector? = null,
    painter: Painter? = null,
    title: String,
    value: String,
    onClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .height(50.dp)
            .padding(horizontal = 15.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        icon?.let {
            Icon(
                imageVector = it,
                tint = Color.Gray,
                contentDescription = stringResource(id = R.string.desc_icon),
                modifier = Modifier.height(40.dp)
            )
        }
        painter?.let {
            Icon(
                painter = it,
                tint = Color.Gray,
                contentDescription = stringResource(id = R.string.desc_icon),
                modifier = Modifier.height(40.dp)
            )
        }
        Spacer(modifier = Modifier.width(15.dp))
        Text(
            text = title,
            color = Color.White,
            style = MaterialTheme.typography.subtitle1,
        )
        Spacer(Modifier.weight(1f))
        Text(text = value, color = Color.White)
    }
}