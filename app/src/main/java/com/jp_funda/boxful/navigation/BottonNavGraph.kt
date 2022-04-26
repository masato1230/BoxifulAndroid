package com.jp_funda.boxful.navigation

//import androidx.navigation.compose.composable
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.jp_funda.boxful.models.SingleMenu
import com.jp_funda.boxful.views.MainContent
import com.jp_funda.boxful.views.home.HomeScreen

@ExperimentalAnimationApi
@ExperimentalPermissionsApi
@Composable
fun BottomNavGraph(navController: NavHostController, bottomBarState: MutableState<Boolean>) {
    AnimatedNavHost(
        navController = navController,
        startDestination = BottomBarMenuItem.Home.route,
    ) {
        /** Home Screen. */
        composable(route = BottomBarMenuItem.Home.route) {
            bottomBarState.value = true
            HomeScreen(navController)
        }

        /** Training Screen. */
        /** Training Screen. */
        val singleMenuKey = "singleMenu"
        composable(
            route = "training/{$singleMenuKey}",
            arguments = listOf(navArgument(singleMenuKey) { type = NavType.StringType }),
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            }
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