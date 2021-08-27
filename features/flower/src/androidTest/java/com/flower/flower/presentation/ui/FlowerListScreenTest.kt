package com.flower.flower.presentation.ui

import android.content.Context
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsNotFocused
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import com.flower.base.domain.model.UIComponent
import com.flower.base.utils.TAG_INPUT_FIELD
import com.flower.flower.R
import com.flower.flower.domain.repo.IFlowersRepo
import com.flower.flower.domain.usecase.GetFlowersUseCase
import com.flower.flower.domain.usecase.SearchFlowersUserCase
import com.flower.flower.presentation.ui.flowersList.FlowersListScreen
import com.flower.flower.presentation.ui.flowersList.FlowersListState
import com.flower.flower.presentation.ui.flowersList.FlowersListViewModel
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FlowerListScreenTest {

    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var viewModel: FlowersListViewModel

    private lateinit var getFlowersUseCase: GetFlowersUseCase

    private lateinit var searchFlowersUserCase: SearchFlowersUserCase

    @RelaxedMockK
    private lateinit var iFlowersRepo: IFlowersRepo

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        getFlowersUseCase = GetFlowersUseCase(iFlowersRepo)
        searchFlowersUserCase = SearchFlowersUserCase(iFlowersRepo)

        viewModel = FlowersListViewModel(
            getFlowersUseCase = getFlowersUseCase,
            searchFlowersUserCase = searchFlowersUserCase
        )
    }

    @Test
    fun initialViewsAreDisplayed() {
        composeTestRule.setContent {
            FlowersListScreen(
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent
            )
        }

        composeTestRule.run {
            onNodeWithContentDescription("listHeader").assertExists()
            onNodeWithContentDescription("iconSearch").assertExists()
            onNodeWithContentDescription("iconSearch").assertHasClickAction()
            onNodeWithTag(TAG_INPUT_FIELD).assertIsNotFocused()
            onNodeWithText(context.getString(R.string.discover_flowers)).assertExists()
            onNodeWithText(context.getString(R.string.explore_flowers_info)).assertExists()
        }
    }

    @Test
    fun errorDialogIsDisplayed() {
        composeTestRule.setContent {
            FlowersListScreen(
                state = FlowersListState(
                    dialog = UIComponent.Dialog(description = R.string.something_went_wrong),
                    dialogVisibility = true
                ),
                events = viewModel::onTriggerEvent
            )
        }

        composeTestRule.run {
            onNodeWithText(context.getString(R.string.something_went_wrong)).assertExists()
            onNodeWithText(context.getString(R.string.ok)).assertExists()
            onNodeWithText(context.getString(R.string.ok)).assertHasClickAction()
        }
    }
}
