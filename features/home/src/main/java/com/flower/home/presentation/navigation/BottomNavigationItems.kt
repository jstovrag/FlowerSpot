package com.flower.home.presentation.navigation

import com.flower.home.R
import com.flower.home.presentation.navigation.HomeRoutes.FAVORITE_FLOWERS_ROUTE
import com.flower.home.presentation.navigation.HomeRoutes.NEW_SIGHTING_ROUTE
import com.flower.home.presentation.navigation.HomeRoutes.SIGHTING_DETAIL_ROUTE
import com.flower.home.presentation.navigation.HomeRoutes.SIGHTING_LIST_ROUTE

object HomeRoutes {
    const val FAVORITE_FLOWERS_ROUTE = "favoriteFlowers"
    const val SIGHTING_DETAIL_ROUTE = "sightingDetail"
    const val NEW_SIGHTING_ROUTE = "newSighting"
    const val SIGHTING_LIST_ROUTE = "sightingList"
}

sealed class BottomNavigationItems(val route: String, val icon: Int) {
    object FavoriteFlowers : BottomNavigationItems(FAVORITE_FLOWERS_ROUTE, R.drawable.ic_favorite)
    object SightingDetail : BottomNavigationItems(SIGHTING_DETAIL_ROUTE, R.drawable.ic_comment)
    object NewSighting : BottomNavigationItems(NEW_SIGHTING_ROUTE, R.drawable.ic_new_sighting)
    object SightingList : BottomNavigationItems(SIGHTING_LIST_ROUTE, R.drawable.ic_sighting_list)
}
