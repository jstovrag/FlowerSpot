package com.flower.flower.data.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import com.flower.flower.data.entity.FakeFlowersList
import com.flower.flower.data.mapper.FlowersListEntityToModelMapper
import com.flower.flower.data.remote.FlowersRemoteSource
import com.flower.remote.scheduler.TestSchedulerProvider
import com.flower.remote.utils.DataSource
import com.flower.remote.utils.ErrorCodesMapper
import com.flower.remote.utils.FailureData
import com.flower.remote.utils.FailureView
import com.flower.remote.utils.NetworkCodes.CONNECTION_ERROR
import com.flower.remote.utils.Resource
import com.jraska.livedata.test
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import java.io.IOException

@ExperimentalCoroutinesApi
class FlowersRepoTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    private lateinit var repo: FlowersRepo
    private lateinit var fakeFlowersList: FakeFlowersList
    private lateinit var mapper: FlowersListEntityToModelMapper

    @RelaxedMockK
    private lateinit var remoteDataSource: FlowersRemoteSource

    @RelaxedMockK
    private lateinit var errorCodesMapper: ErrorCodesMapper

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testCoroutineDispatcher)

        fakeFlowersList = FakeFlowersList()
        mapper = FlowersListEntityToModelMapper()

        repo = FlowersRepo(
            flowersRemoteSource = remoteDataSource,
            schedulerProvider = TestSchedulerProvider(),
            errorCodesMapper = errorCodesMapper,
            flowersMapper = mapper
        )
    }

    @InternalCoroutinesApi
    @Test
    fun `Given remote, when getFlowers with RemoteData, then get loading - success`() =
        runBlockingTest {
            val response = fakeFlowersList.getFlowersListResponse()
            coEvery { remoteDataSource.getFlowers() } returns response

            val resultLiveData = repo.getFlowers().asLiveData().test()

            resultLiveData.assertHistorySize(2)
                .assertValueHistory(
                    Resource.Loading,
                    Resource.Success(
                        data = mapper.map(response),
                        source = DataSource.REMOTE
                    )
                )
        }

    @InternalCoroutinesApi
    @Test
    fun `Given remote, when getFlowers with RemoteData, then get loading - failure`() =
        runBlockingTest {
            val errorCode = CONNECTION_ERROR
            val errorScript = "Failed to connect to server please check you network!"
            coEvery { errorCodesMapper.getMessage(errorCode) } returns errorScript
            coEvery { remoteDataSource.getFlowers() } throws IOException("")

            val resultLiveData = repo.getFlowers().asLiveData().test()

            resultLiveData.assertHistorySize(2)
                .assertValueHistory(
                    Resource.Loading,
                    Resource.Failure(FailureView.Dialog, FailureData(errorCode, errorScript))
                )
        }

    @InternalCoroutinesApi
    @Test
    fun `Given remote, when searchFlowers with RemoteData, then get loading - success`() =
        runBlockingTest {
            val response = fakeFlowersList.getFlowersListResponse()
            coEvery { remoteDataSource.searchFlowers("test") } returns response

            val resultLiveData = repo.searchFlowers("test").asLiveData().test()

            resultLiveData.assertHistorySize(2)
                .assertValueHistory(
                    Resource.Loading,
                    Resource.Success(
                        data = mapper.map(response),
                        source = DataSource.REMOTE
                    )
                )
        }

    @InternalCoroutinesApi
    @Test
    fun `Given remote, when searchFlowers with RemoteData, then get loading - failure`() =
        runBlockingTest {
            val errorCode = CONNECTION_ERROR
            val errorScript = "Failed to connect to server please check you network!"
            coEvery { errorCodesMapper.getMessage(errorCode) } returns errorScript
            coEvery { remoteDataSource.searchFlowers("test") } throws IOException("")

            val resultLiveData = repo.searchFlowers("test").asLiveData().test()

            resultLiveData.assertHistorySize(2)
                .assertValueHistory(
                    Resource.Loading,
                    Resource.Failure(FailureView.Dialog, FailureData(errorCode, errorScript))
                )
        }
}
