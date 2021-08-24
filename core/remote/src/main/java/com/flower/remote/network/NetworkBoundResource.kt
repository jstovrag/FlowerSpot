package com.flower.remote.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.flower.remote.scheduler.SchedulerProvider
import com.flower.remote.utils.*

abstract class NetworkBoundResource<T>(
    private val schedulerProvider: SchedulerProvider,
    private val errorCodesMapper: ErrorCodesMapper,
) {

    fun asFlow(): Flow<Resource<T>> = flow {
        emit(Resource.Loading)
        val remoteResponse =
            safeApiCall(dispatcher = schedulerProvider.io(), errorCodesMapper = errorCodesMapper) {
                remoteFetch()
            }

        when (remoteResponse) {
            is ResultWrapper.Success -> {
                emit(Resource.Success(data = remoteResponse.value, DataSource.REMOTE))
            }

            is ResultWrapper.Failure -> {
                emit(
                    Resource.Failure(
                        failureData = FailureData(remoteResponse.code, remoteResponse.message)
                    )
                )
            }
        }
    }

    abstract suspend fun remoteFetch(): T?
}
