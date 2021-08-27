package com.flower.home.presentation.ui.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.flower.base.components.FlowerSpotToolbar
import com.flower.base.utils.ToolbarTheme
import com.flower.base.utils.ToolbarType
import com.flower.home.R
import com.flower.home.presentation.navigation.BottomNavigation

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun HomeScreen() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            FlowerSpotToolbar(
                type = ToolbarType(
                    theme = ToolbarTheme.Logo,
                    leftIcon = R.drawable.ic_menu,
                ),
                toolbarElevation = 2.dp
            )
        },
        bottomBar = {
            BottomNavigation(navController)
        }
    ) { innerPadding ->
        HomeNavHost(navController, innerPadding)
    }
}
