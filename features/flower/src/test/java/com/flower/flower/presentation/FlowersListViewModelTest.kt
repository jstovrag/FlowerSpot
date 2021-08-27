package com.flower.flower.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.flower.base.domain.model.UIComponent
import com.flower.flower.R
import com.flower.flower.data.entity.FakeFlowersList
import com.flower.flower.domain.usecase.GetFlowersUseCase
import com.flower.flower.domain.usecase.SearchFlowersUserCase
import com.flower.flower.presentation.ui.flowersList.FlowersListViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class FlowersListViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    private lateinit var viewModel: FlowersListViewModel

    @RelaxedMockK
    private lateinit var getFlowersUseCase: GetFlowersUseCase

    @RelaxedMockK
    private lateinit var searchFlowersUserCase: SearchFlowersUserCase

    private var fakeData = FakeFlowersList()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testCoroutineDispatcher)

        viewModel = FlowersListViewModel(
            getFlowersUseCase = getFlowersUseCase,
            searchFlowersUserCase = searchFlowersUserCase
        )
    }

    @Test
    fun `Given viewModel, when invoke, then loading - success`() = runBlockingTest {
        coEvery { getFlowersUseCase.execute() } returns fakeData.getSuccessEmit()
        val expectedResult = fakeData.getMappedData()

        viewModel.getFlowers()

        viewModel.state.run {
            assert(value == value.copy(isLoading = false))
            assert(value.flowers == expectedResult)
        }
    }

    @Test
    fun `Given viewModel, when invoke, then loading - error`() = runBlockingTest {
        coEvery { getFlowersUseCase.execute() } returns fakeData.getFailureEmit()

        viewModel.getFlowers()

        viewModel.state.run {
            assert(value == value.copy(isLoading = false))
            assert(value == value.copy(dialogVisibility = true))
            assert(value == value.copy(dialog = UIComponent.Dialog(description = R.string.something_went_wrong)))
            assert(value.flowers == null)
        }
    }
}
