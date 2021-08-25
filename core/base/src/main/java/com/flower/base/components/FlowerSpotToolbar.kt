package com.flower.base.components

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flower.base.R
import com.flower.base.utils.TAG_ICON_LEFT
import com.flower.base.utils.ToolbarTheme
import com.flower.base.utils.ToolbarType
import com.flower.theme.Black
import com.flower.theme.White

@ExperimentalAnimationApi
@Composable
fun FlowerSpotToolbar(
    type: ToolbarType = ToolbarType(ToolbarTheme.None),
    titleTextStyleParameter: TextStyle = TextStyle(
        color = White, fontSize = 12.sp,
        fontFamily = FontFamily(
            Font(R.font.ubuntu_regular)
        )
    ),
    toolbarElevation: Dp = 0.dp,
    leftIconEnabled: Boolean = true,
    onLeftIconClick: () -> Unit = {}
) {
    var colorBg = White
    val leftIcon = type.leftIcon
    val topAppBarModifier = Modifier.height(80.dp)
    val topAppBarContentModifier = Modifier.padding(top = 25.dp)
    var titleTextStyle = titleTextStyleParameter
    val isLeftIconVisible = remember { mutableStateOf(false) }

    fun setTheme() {
        when (type.theme) {
            ToolbarTheme.White -> {
                colorBg = White
                titleTextStyle = TextStyle(
                    color = Black, fontSize = 12.sp,
                    fontFamily = FontFamily(
                        Font(R.font.ubuntu_medium)
                    )
                )
            }
            ToolbarTheme.Logo -> {
                colorBg = White
            }
            ToolbarTheme.None -> {
            }
        }

        if(type.navHostController != null)
            type.navHostController.addOnDestinationChangedListener { controller, _, _ ->
                isLeftIconVisible.value = controller.backQueue.size > 2
            }
        else isLeftIconVisible.value = leftIcon != null
    }

    TopAppBar(
        modifier = topAppBarModifier,
        elevation = toolbarElevation,
        backgroundColor = if (type.theme != ToolbarTheme.None) {
            setTheme()
            colorBg
        } else {
            colorBg
        }
    ) {
//        if (type.theme == ToolbarTheme.Logo) {
//            ImageLogo()
//        } else {
        TopAppBarContent(
            topAppBarContentModifier,
            type,
            isLeftIconVisible,
            onLeftIconClick,
            leftIconEnabled,
            leftIcon,
            titleTextStyle
        )
//        }
    }
}

@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun PreviewFlowerSpotToolbar() {
    FlowerSpotToolbar(
        type = ToolbarType(
            ToolbarTheme.Logo
        )
    )
}

//@Composable
//private fun ImageLogo() {
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(top = 25.dp),
//        contentAlignment = Alignment.Center
//    ) {
//        Image(
//            painter = painterResource(R.drawable.ic_logo),
//            contentDescription = "Logo"
//        )
//    }
//}

@SuppressLint("ModifierParameter")
@ExperimentalAnimationApi
@Composable
private fun TopAppBarContent(
    topAppBarContentModifier: Modifier,
    type: ToolbarType,
    isLeftIconVisible: MutableState<Boolean>,
    onLeftIconClick: () -> Unit,
    leftIconEnabled: Boolean,
    leftIcon: Int?,
    titleTextStyle: TextStyle
) {
    Box(topAppBarContentModifier) {
        if (type.theme == ToolbarTheme.Logo) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                painter = painterResource(R.drawable.ic_logo),
                contentDescription = "Logo"
            )
        } else {
            Title(type, titleTextStyle)
        }

        LeftIcon(
            type = type,
            isLeftIconVisible = isLeftIconVisible,
            onLeftIconClick = onLeftIconClick,
            leftIconEnabled = leftIconEnabled,
            leftIcon = leftIcon
        )
    }
}

@ExperimentalAnimationApi
@Composable
private fun LeftIcon(
    type: ToolbarType,
    isLeftIconVisible: MutableState<Boolean>,
    onLeftIconClick: () -> Unit,
    leftIconEnabled: Boolean,
    leftIcon: Int?
) {
    AnimatedVisibility(
        visible = (isLeftIconVisible.value),
        enter = fadeIn(initialAlpha = 0.4f),
        exit = fadeOut(animationSpec = tween(durationMillis = 250))
    ) {
        Row(
            modifier = Modifier.fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                modifier = Modifier.testTag(TAG_ICON_LEFT),
                onClick = {
                    if (type.navHostController != null)
                        type.navHostController.navigateUp()
                    onLeftIconClick()
                },
                enabled = leftIconEnabled,
            ) {
                Image(
                    painter = painterResource(leftIcon!!),
                    contentDescription = "LeftIcon"
                )
            }
        }
    }
}

@Composable
private fun Title(
    type: ToolbarType,
    titleTextStyle: TextStyle
) {
    Row(
        Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            style = titleTextStyle,
            textAlign = TextAlign.Center,
            maxLines = 1,
            text = type.titleTxt ?: stringResource(id = R.string.empty)
        )
    }
}
