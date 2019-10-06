package com.example.pai.network

import com.squareup.moshi.Json

data class EventCategory(
    val name: String,
    @Json(name = "imageUrl") val imageUrl: String
)