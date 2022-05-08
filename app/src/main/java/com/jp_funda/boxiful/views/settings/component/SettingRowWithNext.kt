package com.jp_funda.boxiful.views.settings.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jp_funda.boxiful.R

/** SettingRow with next screen */
@Composable
fun SettingRowWithNext(
    icon: ImageVector,
    title: String,
    selectedValue: String? = null,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .height(50.dp)
            .padding(horizontal = 15.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = icon,
            tint = Color.Gray,
            contentDescription = stringResource(id = R.string.desc_icon),
            modifier = Modifier.height(40.dp)
        )
        Spacer(modifier = Modifier.width(15.dp))
        Text(
            text = title,
            color = Color.White,
            style = MaterialTheme.typography.subtitle1,
        )
        Spacer(Modifier.weight(1f))
        selectedValue?.let { Text(text = it, color = Color.White) }
        Spacer(modifier = Modifier.width(10.dp))
        Icon(
            imageVector = Icons.Default.ArrowForward,
            tint = Color.Gray,
            contentDescription = stringResource(id = R.string.desc_right)
        )
    }
}