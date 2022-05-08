package com.jp_funda.boxiful.views.oss_licenses

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jp_funda.boxiful.views.components.WebView

@Composable
fun OssLicensesScreen() {
    WebView(url = "file:///android_asset/licenses.html", modifier = Modifier.fillMaxSize())
}