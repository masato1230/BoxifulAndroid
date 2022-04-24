package com.jp_funda.boxful.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.jp_funda.boxful.views.MainContent
import com.jp_funda.boxful.views.home.HomeScreen

@ExperimentalPermissionsApi
@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarMenuItem.Home.route,
    ) {
        /** Home Screen. */
        composable(route = BottomBarMenuItem.Home.route) {
            HomeScreen(navController)
        }

        /** Training Screen. */
        composable(route = "training") {
            MainContent()
        }

        composable(route = BottomBarMenuItem.Record.route) {
            // TODO change
            MainContent()
        }
        composable(route = BottomBarMenuItem.Settings.route) {
            // TODO change
            MainContent()
        }
    }
}