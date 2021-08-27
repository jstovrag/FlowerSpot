package com.flower.flower.data.repo

import com.flower.flower.data.mapper.FlowersListEntityToModelMapper
import com.flower.flower.data.remote.IFlowersRemoteSource
import com.flower.flower.domain.model.FlowersListModel
import com.flower.flower.domain.repo.IFlowersRepo
import com.flower.remote.network.NetworkBoundResource
import com.flower.remote.scheduler.ISchedulerProvider
import com.flower.remote.utils.ErrorCodesMapper
import com.flower.remote.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FlowersRepo @Inject constructor(
    private val flowersRemoteSource: IFlowersRemoteSource,
    private val schedulerProvider: ISchedulerProvider,
    private val errorCodesMapper: ErrorCodesMapper,
    private val flowersMapper: FlowersListEntityToModelMapper
) : IFlowersRepo {

    override suspend fun getFlowers(): Flow<Resource<FlowersListModel>> {
        return object : NetworkBoundResource<FlowersListModel>(
            schedulerProvider,
            errorCodesMapper
        ) {
            override suspend fun remoteFetch(): FlowersListModel {
                return flowersRemoteSource.getFlowers().let {
                    flowersMapper.map(it)
                }
            }
        }.asFlow()
    }

    override suspend fun searchFlowers(searchText: String): Flow<Resource<FlowersListModel>> {
        return object : NetworkBoundResource<FlowersListModel>(
            schedulerProvider,
            errorCodesMapper
        ) {
            override suspend fun remoteFetch(): FlowersListModel {
                return flowersRemoteSource.searchFlowers(searchText).let {
                    flowersMapper.map(it)
                }
            }
        }.asFlow()
    }
}
