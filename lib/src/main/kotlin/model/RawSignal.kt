package dev.hossain.timeline.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RawSignal(
    val activityRecord: ActivityRecord?,
    val position: Position?
)

@JsonClass(generateAdapter = true)
data class ActivityRecord(
    val probableActivities: List<ProbableActivity> = emptyList(),
    val timestamp: String
)

@JsonClass(generateAdapter = true)
data class ProbableActivity(
    /**
     * Possible values:
     *
     * - EXITING_VEHICLE
     * - IN_RAIL_VEHICLE
     * - IN_ROAD_VEHICLE
     * - IN_VEHICLE
     * - ON_BICYCLE
     * - ON_FOOT
     * - RUNNING
     * - STILL
     * - TILTING
     * - UNKNOWN
     * - WALKING
     */
    val type: String,
    val confidence: Double
)

@JsonClass(generateAdapter = true)
data class Position(
    @Json(name = "LatLng") val latLng: String,
    val accuracyMeters: Int,
    val altitudeMeters: Double,
    val source: String,
    val timestamp: String,
    val speedMetersPerSecond: Double
)