package com.jp_funda.boxiful.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.jp_funda.boxiful.R

sealed class BottomBarMenuItem(
    val route: String,
    @StringRes
    val titleRes: Int,
    val icon: ImageVector,
) {
    object Home: BottomBarMenuItem(
        route = NavigationRoutes.HOME,
        titleRes = R.string.home,
        icon = Icons.Default.Home,
    )
    object WayToUse: BottomBarMenuItem(
      route = NavigationRoutes.WAY_TO_USE,
        titleRes = R.string.way_to_use,
        icon = Icons.Default.Info,
    )
    object Record: BottomBarMenuItem(
        route = NavigationRoutes.RECORD,
        titleRes = R.string.record,
        icon = Icons.Default.List,
    )
    object Settings: BottomBarMenuItem(
        route = NavigationRoutes.SETTINGS,
        titleRes = R.string.settings,
        icon = Icons.Default.Settings,
    )
}