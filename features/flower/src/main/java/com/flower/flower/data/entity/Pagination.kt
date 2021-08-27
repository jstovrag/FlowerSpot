package com.flower.flower.data.entity

import com.squareup.moshi.Json

data class Pagination(
    @Json(name = "current_page") val currentPage: Int,
    @Json(name = "prev_page") val prevPage: Int?,
    @Json(name = "next_page") val nextPage: Int?,
    @Json(name = "total_pages") val totalPages: Int,
)
