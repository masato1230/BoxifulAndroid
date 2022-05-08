package com.jp_funda.boxiful.views.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jp_funda.boxiful.views.settings.component.CopyrightSection

@Composable
fun SettingsScreen() {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        CopyrightSection()
    }
}