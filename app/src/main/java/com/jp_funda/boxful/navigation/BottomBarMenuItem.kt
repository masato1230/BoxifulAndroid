package com.jp_funda.boxful.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.jp_funda.boxful.R

sealed class BottomBarMenuItem(
    val route: String,
    @StringRes
    val titleResource: Int,
    val icon: ImageVector,
) {
    object Home: BottomBarMenuItem(
        route = "home",
        titleResource = R.string.home,
        icon = Icons.Default.Home,
    )
    object Record: BottomBarMenuItem(
        route = "record",
        titleResource = R.string.record,
        icon = Icons.Default.List,
    )
    object Settings: BottomBarMenuItem(
        route = "settings",
        titleResource = R.string.settings,
        icon = Icons.Default.Settings,
    )
}