package dev.hossain.timeline.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserLocationProfile(
    val frequentPlaces: List<FrequentPlace>
)

@JsonClass(generateAdapter = true)
data class FrequentPlace(
    val placeId: String,
    val placeLocation: String,
    val label: String
)