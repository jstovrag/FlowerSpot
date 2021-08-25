package com.flower.home.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.flower.theme.LightRed

@ExperimentalAnimationApi
@Composable
fun BottomNavigation(
    navController: NavHostController = rememberNavController(),
) {
    val bottomNavigationItems = listOf(
        BottomNavigationItems.FavoriteFlowers,
        BottomNavigationItems.SightingDetail,
        BottomNavigationItems.NewSighting,
        BottomNavigationItems.SightingList,
    )
    BottomNavigation(backgroundColor = Color.White, contentColor = Color.Black) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        bottomNavigationItems.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(id = screen.icon),
                        contentDescription = "bottomNavIcon"
                    )
                },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    if (currentDestination?.route != screen.route)
                        navController.navigate(screen.route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }

                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                },
                selectedContentColor = LightRed,
                unselectedContentColor = LightRed
            )
        }
    }
}

@ExperimentalAnimationApi
@Preview
@Composable
fun BottomNavigationPreview() {
    BottomNavigation()
}
