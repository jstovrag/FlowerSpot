package com.flower.home.presentation.ui.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.flower.home.presentation.navigation.BottomNavigationItems

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun HomeNavHost(
    navController: NavHostController = rememberNavController(),
    innerPadding: PaddingValues = PaddingValues(),
) {

    NavHost(
        navController = navController,
        startDestination = BottomNavigationItems.FavoriteFlowers.route,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(BottomNavigationItems.FavoriteFlowers.route) {

        }
        composable(BottomNavigationItems.SightingDetail.route) {

        }
        composable(BottomNavigationItems.NewSighting.route) {

        }
        composable(BottomNavigationItems.SightingList.route) {

        }
    }
}
