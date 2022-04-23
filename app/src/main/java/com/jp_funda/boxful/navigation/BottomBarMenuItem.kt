package com.jp_funda.boxful.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarMenuItem(
    val route: String,
    val title: String,
    val icon: ImageVector,
) {
    object Home: BottomBarMenuItem(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home,
    )
    object Record: BottomBarMenuItem(
        route = "record",
        title = "Record",
        icon = Icons.Default.List,
    )
    object Settings: BottomBarMenuItem(
        route = "settings",
        title = "Settings",
        icon = Icons.Default.Settings,
    )
}