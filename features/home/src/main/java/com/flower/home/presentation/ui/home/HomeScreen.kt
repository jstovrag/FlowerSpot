package com.flower.home.presentation.ui.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.flower.base.components.FlowerSpotToolbar
import com.flower.base.utils.ToolbarTheme
import com.flower.base.utils.ToolbarType
import com.flower.home.R
import com.flower.home.presentation.navigation.BottomNavigation
import com.flower.theme.White

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
                ), toolbarElevation = 2.dp
            )
        },
        bottomBar = {
            BottomNavigation(navController)
        }
    ) { innerPadding ->
        Box() {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 6.dp),
                contentScale = ContentScale.Crop,
                painter = painterResource(R.drawable.image_flower_list_header),
                contentDescription = "listHeader"
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 61.dp, start = 76.dp, end = 76.dp),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        color = White, fontSize = 24.sp,
                        fontFamily = FontFamily(
                            Font(R.font.ubuntu_light)
                        )
                    ),
                    text = stringResource(id = R.string.discover_flowers)
                )

                Text(
                    modifier = Modifier
                        .padding(top = 26.dp, start = 34.dp, end = 34.dp),
                    style = TextStyle(
                        color = White, fontSize = 15.sp,
                        fontFamily = FontFamily(
                            Font(R.font.ubuntu_light)
                        )
                    ),
                    text = stringResource(id = R.string.explore_flowers_info),
                    textAlign = TextAlign.Center,
                )
            }
        }
        HomeNavHost(navController, innerPadding)
    }
}
