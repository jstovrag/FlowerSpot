package com.flower.flower.data.remote

import com.flower.flower.data.entity.FlowersListResponse
import javax.inject.Inject

class FlowersRemoteSource @Inject constructor(private val flowersApi: FlowersApi) :
    IFlowersRemoteSource {

    override suspend fun getFlowers(): FlowersListResponse {
        return flowersApi.getFlowers()
    }

    override suspend fun searchFlowers(searchText: String): FlowersListResponse {
        return flowersApi.searchFlowers(searchText)
    }
}
