package com.jp_funda.boxiful.views.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun WebView(modifier: Modifier = Modifier, url: String) {
    val webView = android.webkit.WebView(LocalContext.current)
    webView.loadUrl(url)

    AndroidView(factory = { webView }, modifier = modifier)
}