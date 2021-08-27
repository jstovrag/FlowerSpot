package com.flower.flower.data.remote

import com.flower.flower.data.entity.FlowersListResponse

interface IFlowersRemoteSource {

    suspend fun getFlowers(): FlowersListResponse
    suspend fun searchFlowers(searchText: String): FlowersListResponse
}
