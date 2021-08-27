package com.flower.home.presentation.ui.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.flower.flower.presentation.ui.flowersList.FlowersListScreen
import com.flower.flower.presentation.ui.flowersList.FlowersListViewModel
import com.flower.home.presentation.navigation.BottomNavigationItems
import com.flower.sighting.presentation.ui.newSighting.NewSightingScreen
import com.flower.sighting.presentation.ui.sightingDetail.SightingDetailScreen
import com.flower.sighting.presentation.ui.sightingList.SightingListScreen

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
            val viewModel: FlowersListViewModel = hiltViewModel()
            FlowersListScreen(
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent
            )
        }
        composable(BottomNavigationItems.SightingDetail.route) {
            SightingDetailScreen()
        }
        composable(BottomNavigationItems.NewSighting.route) {
            NewSightingScreen()
        }
        composable(BottomNavigationItems.SightingList.route) {
            SightingListScreen()
        }
    }
}
