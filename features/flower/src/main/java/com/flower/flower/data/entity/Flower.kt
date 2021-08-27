package com.flower.flower.data.entity

import com.squareup.moshi.Json

data class Flower(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "latin_name") val latinName: String,
    @Json(name = "sightings") val sightings: Int,
    @Json(name = "profile_picture") val profilePicture: String,
    @Json(name = "favorite") val favorite: Boolean,
)
