package com.jp_funda.boxful.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.jp_funda.boxful.models.SingleMenu
import com.jp_funda.boxful.views.MainContent
import com.jp_funda.boxful.views.home.HomeScreen

@ExperimentalPermissionsApi
@Composable
fun BottomNavGraph(navController: NavHostController, bottomBarState: MutableState<Boolean>) {
    NavHost(
        navController = navController,
        startDestination = BottomBarMenuItem.Home.route,
    ) {
        /** Home Screen. */
        composable(route = BottomBarMenuItem.Home.route) {
            bottomBarState.value = true
            HomeScreen(navController)
        }

        /** Training Screen. */
        val singleMenuKey = "singleMenu"
        composable(
            route = "training/{$singleMenuKey}",
            arguments = listOf(navArgument(singleMenuKey) { type = NavType.StringType })
        ) { backStackEntry ->
            bottomBarState.value = false
            MainContent(menu = SingleMenu.fromName(backStackEntry.arguments?.getString(singleMenuKey)))
        }

        composable(route = BottomBarMenuItem.Record.route) {
            bottomBarState.value = true
            // TODO change
            MainContent()
        }
        composable(route = BottomBarMenuItem.Settings.route) {
            bottomBarState.value = true
            // TODO change
            MainContent()
        }
    }
}