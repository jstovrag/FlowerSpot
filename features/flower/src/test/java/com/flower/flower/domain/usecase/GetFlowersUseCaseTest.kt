package com.flower.flower.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import com.flower.flower.data.entity.FakeFlowersList
import com.flower.flower.data.mapper.FlowersListEntityToModelMapper
import com.flower.flower.data.repo.FakeFlowersRepo
import com.flower.remote.utils.DataSource
import com.flower.remote.utils.FailureData
import com.flower.remote.utils.NetworkCodes
import com.flower.remote.utils.Resource
import com.jraska.livedata.test
import io.mockk.coEvery
import io.mockk.spyk
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
class GetFlowersUseCaseTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    private lateinit var useCase: GetFlowersUseCase

    private lateinit var fakeRepo: FakeFlowersRepo

    private lateinit var mapper: FlowersListEntityToModelMapper

    private lateinit var fakeData: FakeFlowersList

    @Before
    fun setUp() {
        Dispatchers.setMain(testCoroutineDispatcher)
        fakeRepo = spyk(FakeFlowersRepo())
        mapper = FlowersListEntityToModelMapper()
        fakeData = FakeFlowersList()
        useCase = GetFlowersUseCase(fakeRepo)
    }

    @Test
    fun `Given useCase, when getFlowersList, then get loading - success`() =
        runBlockingTest {
            val expected = mapper.map(fakeData.getFlowersListResponse())

            val resultLiveData = useCase.execute().asLiveData().test()

            resultLiveData.assertHistorySize(2)
                .assertValueHistory(
                    Resource.Loading,
                    Resource.Success(expected, DataSource.REMOTE)
                )
        }

    @Test
    fun `Given useCase, when getFlowersList, then loading - error`() =
        runBlockingTest {
            val errorCode = NetworkCodes.CONNECTION_ERROR
            val errorScript = "Failed to connect to server please check you network!"

            coEvery { fakeRepo.getFlowers() } returns fakeData.getFailureEmit()

            val resultLiveData = useCase.execute().asLiveData().test()

            resultLiveData.assertHistorySize(2)
                .assertValueHistory(
                    Resource.Loading,
                    Resource.Failure(failureData = FailureData(errorCode, errorScript))
                )
        }
}
