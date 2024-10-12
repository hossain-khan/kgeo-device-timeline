package dev.hossain.timeline.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SemanticSegment(
    val startTime: String,
    val endTime: String,
    val startTimeTimezoneUtcOffsetMinutes: Int?,
    val endTimeTimezoneUtcOffsetMinutes: Int?,
    val timelinePath: List<TimelinePoint> = emptyList(),
    val visit: Visit?
)

@JsonClass(generateAdapter = true)
data class TimelinePoint(
    val point: String,
    val time: String
)

@JsonClass(generateAdapter = true)
data class Visit(
    val hierarchyLevel: Int,
    val probability: Double,
    val topCandidate: TopCandidate
)

@JsonClass(generateAdapter = true)
data class TopCandidate(
    val placeId: String,
    val semanticType: String,
    val probability: Double,
    val placeLocation: PlaceLocation
)

@JsonClass(generateAdapter = true)
data class PlaceLocation(
    val latLng: String
)