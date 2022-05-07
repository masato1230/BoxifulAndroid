package com.jp_funda.boxiful.views.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.ui.theme.Blue500
import com.jp_funda.boxiful.ui.theme.Green500
import java.net.URLEncoder


@Composable
fun SnsShareButtons(shareMessage: String) {
    val context = LocalContext.current

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        // Line share button
        SnsShareButton(
            title = stringResource(id = R.string.sns_share_line),
            iconPainter = painterResource(id = R.drawable.ic_line),
            backgroundColor = Green500,
            contentDescription = stringResource(id = R.string.desc_line_icon)
        ) {
            // Share result at line
            val encodedMessage = URLEncoder.encode(shareMessage, "utf-8")
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.data = Uri.parse("https://line.me/R/share?text=$encodedMessage")
            context.startActivity(intent)
        }
        Spacer(modifier = Modifier.width(10.dp))
        // Twitter share button
        SnsShareButton(
            title = stringResource(id = R.string.sns_share_twitter),
            iconPainter = painterResource(id = R.drawable.ic_twitter),
            backgroundColor = Blue500,
            contentDescription = stringResource(id = R.string.desc_twitter_icon)
        ) {
            // Share result at twitter
            val tweetUrl = ("https://twitter.com/intent/tweet?text=$shareMessage")
            val uri = Uri.parse(tweetUrl)
            context.startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
    }
}

@Composable
fun RowScope.SnsShareButton(
    title: String,
    iconPainter: Painter,
    backgroundColor: Color,
    contentDescription: String,
    onClick: () -> Unit,
) {
    Button(
        modifier = Modifier.weight(0.5f),
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor)
    ) {
        Image(
            painter = iconPainter,
            contentDescription = contentDescription,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.caption,
        )
    }
}