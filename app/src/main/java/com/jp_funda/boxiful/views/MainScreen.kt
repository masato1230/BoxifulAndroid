package com.jp_funda.boxiful.views

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.jp_funda.boxiful.navigation.BottomBarMenuItem
import com.jp_funda.boxiful.navigation.BottomNavGraph
import com.jp_funda.boxiful.ui.theme.Yellow500

@ExperimentalAnimationApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalPermissionsApi
@Composable
fun MainScreen(viewModel: MainViewModel) {
    val navController = rememberAnimatedNavController()
    val bottomBarState = remember { mutableStateOf(true) }

    Scaffold(bottomBar = {
        if (bottomBarState.value) {
            BottomBar(navController = navController)
        }
    }) {
        BottomNavGraph(
            navController = navController,
            bottomBarState = bottomBarState,
            mainViewModel = viewModel,
        )
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val menuItems = listOf(
        BottomBarMenuItem.Home,
        BottomBarMenuItem.Record,
        BottomBarMenuItem.Settings,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(backgroundColor = Color.White) {
        menuItems.forEach { menuItem ->
            AddItem(
                menuItem = menuItem,
                currentDestination = currentDestination,
                navController = navController,
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    menuItem: BottomBarMenuItem,
    currentDestination: NavDestination?,
    navController: NavHostController,
) {
    BottomNavigationItem(
        label = { Text(text = stringResource(id = menuItem.titleRes)) },
        icon = { Icon(imageVector = menuItem.icon, contentDescription = "Navigation Icon") },
        selected = currentDestination?.hierarchy?.any { it.route == menuItem.route } == true,
        onClick = { navController.navigate(menuItem.route) },
        selectedContentColor = Yellow500,
        unselectedContentColor = Color.Gray,
    )
}