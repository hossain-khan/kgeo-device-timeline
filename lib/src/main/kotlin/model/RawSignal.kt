package dev.hossain.timeline.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Represents a raw signal containing activity and position data.
 *
 * Example JSON:
 * ```json
 * {
 *   "activityRecord": {
 *     "probableActivities": [
 *       {
 *         "type": "WALKING",
 *         "confidence": 0.9
 *       },
 *       {
 *         "type": "STILL",
 *         "confidence": 0.1
 *       }
 *     ],
 *     "timestamp": "2023-10-01T08:00:00.000Z"
 *   },
 *   "position": {
 *     "LatLng": "40.712776°, -74.005974°",
 *     "accuracyMeters": 5,
 *     "altitudeMeters": 10.0,
 *     "source": "GPS",
 *     "timestamp": "2023-10-01T08:00:00.000Z",
 *     "speedMetersPerSecond": 1.5
 *   }
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class RawSignal(
    val activityRecord: ActivityRecord?,
    val position: Position?
)

/**
 * Represents an activity record with probable activities and a timestamp.
 *
 * Example JSON:
 * ```json
 * {
 *   "probableActivities": [
 *     {
 *       "type": "WALKING",
 *       "confidence": 0.9
 *     },
 *     {
 *       "type": "STILL",
 *       "confidence": 0.1
 *     }
 *   ],
 *   "timestamp": "2023-10-01T08:00:00.000Z"
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class ActivityRecord(
    val probableActivities: List<ProbableActivity> = emptyList(),
    val timestamp: String
)

/**
 * Represents a probable activity with a type and confidence level.
 *
 * Example JSON:
 * ```json
 * {
 *   "type": "WALKING",
 *   "confidence": 0.9
 * }
 * ```
 */
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

/**
 * Represents a position with latitude/longitude, accuracy, altitude, source, timestamp, and speed.
 *
 * Example JSON:
 * ```json
 * {
 *   "LatLng": "40.712776°, -74.005974°",
 *   "accuracyMeters": 5,
 *   "altitudeMeters": 10.0,
 *   "source": "GPS",
 *   "timestamp": "2023-10-01T08:00:00.000Z",
 *   "speedMetersPerSecond": 1.5
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class Position(
    @Json(name = "LatLng") val latLng: String,
    val accuracyMeters: Int,
    val altitudeMeters: Double,
    val source: String,
    val timestamp: String,
    val speedMetersPerSecond: Double
)
