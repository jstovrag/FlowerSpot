package com.flower.flower.data.repo

import com.flower.flower.data.entity.FakeFlowersList
import com.flower.flower.data.mapper.FlowersListEntityToModelMapper
import com.flower.flower.domain.model.FlowersListModel
import com.flower.flower.domain.repo.IFlowersRepo
import com.flower.remote.utils.DataSource
import com.flower.remote.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeFlowersRepo : IFlowersRepo {

    private val mapper = FlowersListEntityToModelMapper()
    private val fakeData = FakeFlowersList()
    override suspend fun getFlowers(): Flow<Resource<FlowersListModel>> {
        return flow {
            emit(Resource.Loading)
            emit(
                Resource.Success(
                    mapper.map(fakeData.getFlowersListResponse()), DataSource.REMOTE
                )
            )
        }
    }

    override suspend fun searchFlowers(searchText: String): Flow<Resource<FlowersListModel>> {
        return flow {
            emit(Resource.Loading)
            emit(
                Resource.Success(
                    mapper.map(fakeData.getFlowersListResponse()), DataSource.REMOTE
                )
            )
        }
    }
}
