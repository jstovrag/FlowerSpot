package com.flower.flower.data.entity

import com.squareup.moshi.Json

data class FlowersListResponse(
    @Json(name = "flowers") val flowers: List<Flower>,
    @Json(name = "meta") val meta: Meta
)
