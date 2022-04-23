package com.jp_funda.boxful.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector,
) {
    object Home: BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home,
    )
    object Record: BottomBarScreen(
        route = "record",
        title = "Record",
        icon = Icons.Default.List,
    )
    object Settings: BottomBarScreen(
        route = "settings",
        title = "Settings",
        icon = Icons.Default.Settings,
    )
}