package com.jp_funda.boxiful.views.oss_licenses

import androidx.compose.runtime.Composable
import com.jp_funda.boxiful.views.components.WebView

@Composable
fun OssLicensesScreen() {
    WebView(url = "file:///android_asset/licenses.html")
}