package com.flower.base.presentation.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.flower.base.components.FlowerSpotCircularProgressIndicator
import com.flower.base.utils.TAG_PROGRESS_INDICATOR
import org.junit.Rule
import org.junit.Test

class FlowerSpotCircularProgressIndicatorTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun circularIndicatorIsVisible() {
        composeTestRule.setContent {
            FlowerSpotCircularProgressIndicator(
                isLoading = true
            )
        }
        composeTestRule.onNodeWithTag(TAG_PROGRESS_INDICATOR).assertExists()
    }

    @Test
    fun circularIndicatorIsNotVisible() {
        composeTestRule.setContent {
            FlowerSpotCircularProgressIndicator(
                isLoading = false
            )
        }
        composeTestRule.onNodeWithTag(TAG_PROGRESS_INDICATOR).assertDoesNotExist()
    }
}
