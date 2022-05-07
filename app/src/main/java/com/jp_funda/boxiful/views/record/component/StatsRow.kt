package com.jp_funda.boxiful.views.record.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jp_funda.boxiful.R

/** Row for show one stats field. */
@Composable
fun StatsRow(
    modifier: Modifier = Modifier,
    vectorIcon: ImageVector? = null,
    painterIcon: Painter? = null,
    iconColor: Color,
    labelString: String,
    valueString: String,
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Spacer(modifier = Modifier.width(5.dp))
        vectorIcon?.let {
            Icon(
                imageVector = it,
                contentDescription = stringResource(id = R.string.desc_icon),
                tint = iconColor,
                modifier = Modifier.padding(vertical = 2.dp)
            )
        }
        painterIcon?.let {
            Icon(
                painter = it,
                contentDescription = stringResource(id = R.string.desc_icon),
                tint = iconColor,
                modifier = Modifier.padding(vertical = 2.dp)
            )
        }
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = labelString,
            color = Color.DarkGray,
            fontWeight = FontWeight.ExtraBold,
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(text = valueString)
        Spacer(modifier = Modifier.width(5.dp))
    }
}