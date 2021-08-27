package com.flower.base.utils.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.google.accompanist.navigation.animation.composable

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
fun NavGraphBuilder.addScreen(route: String, content: @Composable (NavBackStackEntry) -> Unit) {
    composable(
        route,
        popExitTransition = { _, _ -> defaultPopExitTransition() },
        enterTransition = { _, _ -> defaultEnterTransition() },
        popEnterTransition = { _, _ -> defaultPopEnterTransition() },
        exitTransition = { _, _ -> defaultExitTransition() },
        content = content
    )
}

@ExperimentalAnimationApi
fun defaultPopExitTransition(): ExitTransition {
    return slideOutHorizontally(
        targetOffsetX = { 300 },
        animationSpec = tween(
            durationMillis = 200,
            easing = LinearEasing
        )
    )
}

@ExperimentalAnimationApi
fun defaultEnterTransition(): EnterTransition {
    return slideInHorizontally(
        initialOffsetX = { 300 },
        animationSpec = tween(
            durationMillis = 200,
            easing = LinearEasing
        )
    )
}

@ExperimentalAnimationApi
fun defaultPopEnterTransition(): EnterTransition {
    return slideInHorizontally(
        initialOffsetX = { -300 },
        animationSpec = tween(
            durationMillis = 200,
            easing = LinearEasing
        )
    )
}

@ExperimentalAnimationApi
fun defaultExitTransition(): ExitTransition {
    return slideOutHorizontally(
        targetOffsetX = { -300 },
        animationSpec = tween(
            durationMillis = 200,
            easing = LinearEasing
        )
    )
}
