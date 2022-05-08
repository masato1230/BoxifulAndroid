package com.jp_funda.boxiful.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.jp_funda.boxiful.extensions.getLeftSlideInTransaction
import com.jp_funda.boxiful.extensions.getLeftSlideOutTransaction
import com.jp_funda.boxiful.extensions.getRightSlideInTransaction
import com.jp_funda.boxiful.extensions.getRightSlideOutTransaction
import com.jp_funda.boxiful.models.SingleMenu
import com.jp_funda.boxiful.views.MainViewModel
import com.jp_funda.boxiful.views.home.HomeScreen
import com.jp_funda.boxiful.views.login.LoginScreen
import com.jp_funda.boxiful.views.my_page.MyPageScreen
import com.jp_funda.boxiful.views.oss_licenses.OssLicensesScreen
import com.jp_funda.boxiful.views.record.RecordScreen
import com.jp_funda.boxiful.views.result.ResultScreen
import com.jp_funda.boxiful.views.settings.SettingsScreen
import com.jp_funda.boxiful.views.training.TrainingScreen

@ExperimentalAnimationApi
@ExperimentalPermissionsApi
@Composable
fun BottomNavGraph(
    navController: NavHostController,
    bottomBarState: MutableState<Boolean>,
    mainViewModel: MainViewModel,
) {
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
        val singleMenuKey = "singleMenu"
        composable(
            route = "${NavigationRoutes.TRAINING}/{$singleMenuKey}",
            arguments = listOf(navArgument(singleMenuKey) { type = NavType.StringType }),
            enterTransition = { getLeftSlideInTransaction() },
            exitTransition = { getLeftSlideOutTransaction() },
            popEnterTransition = { getRightSlideInTransaction() },
            popExitTransition = { getRightSlideOutTransaction() },
        ) { backStackEntry ->
            bottomBarState.value = false
            TrainingScreen(
                navController = navController,
                menu = SingleMenu.fromName(backStackEntry.arguments?.getString(singleMenuKey))
                    ?: SingleMenu.NormalMenu,
                mainViewModel = mainViewModel,
            )
        }

        /** Result Screen. */
        composable(
            route = NavigationRoutes.RESULT,
            enterTransition = { getLeftSlideInTransaction() },
            exitTransition = { getLeftSlideOutTransaction() },
            popEnterTransition = { getRightSlideInTransaction() },
            popExitTransition = { getRightSlideOutTransaction() },
        ) {
            bottomBarState.value = true
            ResultScreen(navController, mainViewModel.singleMenuScores)
        }

        /** Record Screen. */
        composable(route = BottomBarMenuItem.Record.route) {
            bottomBarState.value = true
            RecordScreen(navController)
        }

        /** Settings Screen. */
        composable(route = BottomBarMenuItem.Settings.route) {
            bottomBarState.value = true
            SettingsScreen(navController)
        }

        /** OSS licenses Screen. */
        composable(
            route = NavigationRoutes.OSS_LICENSES,
            enterTransition = { getLeftSlideInTransaction() },
            exitTransition = { getLeftSlideOutTransaction() },
            popEnterTransition = { getRightSlideInTransaction() },
            popExitTransition = { getRightSlideOutTransaction() },
        ) {
            bottomBarState.value = false
            OssLicensesScreen()
        }

        /** Login Screen. */
        composable(route = NavigationRoutes.LOGIN) {
            LoginScreen(navController = navController)
        }

        /** My Page Screen. */
        composable(route = NavigationRoutes.MY_PAGE) {
            MyPageScreen(navController = navController)
        }
    }
}