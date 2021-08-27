package com.flower.flower.domain.repo

import com.flower.flower.domain.model.FlowersListModel
import com.flower.remote.utils.Resource
import kotlinx.coroutines.flow.Flow

interface IFlowersRepo {

    suspend fun getFlowers(): Flow<Resource<FlowersListModel>>
    suspend fun searchFlowers(searchText: String): Flow<Resource<FlowersListModel>>
}
