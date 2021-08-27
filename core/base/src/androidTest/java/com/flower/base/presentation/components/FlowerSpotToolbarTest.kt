package com.flower.base.presentation.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.flower.base.components.FlowerSpotToolbar
import com.flower.base.utils.ToolbarTheme
import com.flower.base.utils.ToolbarType
import org.junit.Rule
import org.junit.Test

class FlowerSpotToolbarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @ExperimentalAnimationApi
    @Test
    fun toolbarIsSetWithTitleAndClosableIcon() {
        composeTestRule.run {
            setContent {
                FlowerSpotToolbar(
                    type = ToolbarType(
                        theme = ToolbarTheme.Logo
                    )
                )
            }

            onNodeWithContentDescription("Logo").assertExists()
            onNodeWithContentDescription("iconLeft").assertDoesNotExist()
        }
    }
}
