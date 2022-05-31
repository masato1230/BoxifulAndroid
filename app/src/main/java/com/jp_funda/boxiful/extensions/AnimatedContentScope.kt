package com.jp_funda.boxiful.extensions

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry

@ExperimentalAnimationApi
fun AnimatedContentScope<NavBackStackEntry>.getLeftSlideInTransaction(): EnterTransition {
    return slideIntoContainer(
        AnimatedContentScope.SlideDirection.Left,
        animationSpec = tween(700)
    )
}

@ExperimentalAnimationApi
fun AnimatedContentScope<NavBackStackEntry>.getLeftSlideOutTransaction(): ExitTransition {
    return slideOutOfContainer(
        AnimatedContentScope.SlideDirection.Left,
        animationSpec = tween(700)
    )
}

@ExperimentalAnimationApi
fun AnimatedContentScope<NavBackStackEntry>.getRightSlideInTransaction(): EnterTransition {
    return slideIntoContainer(
        AnimatedContentScope.SlideDirection.Right,
        animationSpec = tween(700)
    )
}

@ExperimentalAnimationApi
fun AnimatedContentScope<NavBackStackEntry>.getRightSlideOutTransaction(): ExitTransition {
    return slideOutOfContainer(
        AnimatedContentScope.SlideDirection.Right,
        animationSpec = tween(700)
    )
}