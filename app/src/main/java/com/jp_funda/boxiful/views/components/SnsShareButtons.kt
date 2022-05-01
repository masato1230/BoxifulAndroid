package com.jp_funda.boxiful.views.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.ui.theme.Blue500
import com.jp_funda.boxiful.ui.theme.Green500

@Composable
fun SnsShareButtons() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        // Line share button
        Button(
            modifier = Modifier.weight(0.5f),
            onClick = { /* TODO */ },
            colors = ButtonDefaults.buttonColors(backgroundColor = Green500)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_line),
                contentDescription = stringResource(R.string.desc_line_icon),
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = stringResource(id = R.string.sns_share_line),
                style = MaterialTheme.typography.caption,
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        // Twitter share button
        Button(
            modifier = Modifier.weight(0.5f),
            onClick = { /* TODO */ },
            colors = ButtonDefaults.buttonColors(backgroundColor = Blue500)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_twitter),
                contentDescription = stringResource(R.string.desc_twitter_icon),
                modifier = Modifier.size(24.dp),
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = stringResource(id = R.string.sns_share_twitter),
                style = MaterialTheme.typography.caption,
            )
        }
    }
}