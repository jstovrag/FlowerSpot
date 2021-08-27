package com.flower.flower.data.entity

import com.squareup.moshi.Json

data class Meta(
    @Json(name = "pagination") val pagination: Pagination
)
