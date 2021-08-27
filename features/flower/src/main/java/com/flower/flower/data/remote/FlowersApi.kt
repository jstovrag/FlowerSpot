package com.flower.flower.data.remote

import com.flower.flower.data.entity.FlowersListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FlowersApi {

    @GET("/api/v1/flowers")
    suspend fun getFlowers(): FlowersListResponse

    @GET("/api/v1/flowers/search")
    suspend fun searchFlowers(@Query("query") searchText: String): FlowersListResponse
}
