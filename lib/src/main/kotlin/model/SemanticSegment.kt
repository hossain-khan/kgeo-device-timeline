package dev.hossain.timeline.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Represents a semantic segment with start and end times, timezone offsets, timeline path, and visit information.
 *
 * Example JSON:
 * ```json
 * {
 *   "startTime": "2023-10-01T08:00:00.000Z",
 *   "endTime": "2023-10-01T09:00:00.000Z",
 *   "startTimeTimezoneUtcOffsetMinutes": 0,
 *   "endTimeTimezoneUtcOffsetMinutes": 0,
 *   "timelinePath": [
 *     {
 *       "point": "40.712776°, -74.005974°",
 *       "time": "2023-10-01T08:15:00.000Z"
 *     },
 *     {
 *       "point": "40.713776°, -74.006974°",
 *       "time": "2023-10-01T08:30:00.000Z"
 *     }
 *   ],
 *   "visit": {
 *     "hierarchyLevel": 1,
 *     "probability": 0.85,
 *     "topCandidate": {
 *       "placeId": "ChIJd8BlQ2BZwokRAFUEcm_qrcA",
 *       "semanticType": "INFERRED_WORK",
 *       "probability": 0.95,
 *       "placeLocation": {
 *         "latLng": "40.712776°, -74.005974°"
 *       }
 *     }
 *   }
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class SemanticSegment(
    val startTime: String,
    val endTime: String,
    val startTimeTimezoneUtcOffsetMinutes: Int?,
    val endTimeTimezoneUtcOffsetMinutes: Int?,
    val timelinePath: List<TimelinePoint> = emptyList(),
    val visit: Visit?
)

/**
 * Represents a timeline point with a geographical point and a timestamp.
 *
 * Example JSON:
 * ```json
 * {
 *   "point": "40.712776°, -74.005974°",
 *   "time": "2023-10-01T08:15:00.000Z"
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class TimelinePoint(
    val point: String,
    val time: String
)

/**
 * Represents a visit with hierarchy level, probability, and top candidate.
 *
 * Example JSON:
 * ```json
 * {
 *   "hierarchyLevel": 1,
 *   "probability": 0.85,
 *   "topCandidate": {
 *     "placeId": "ChIJd8BlQ2BZwokRAFUEcm_qrcA",
 *     "semanticType": "INFERRED_WORK",
 *     "probability": 0.95,
 *     "placeLocation": {
 *       "latLng": "40.712776°, -74.005974°"
 *     }
 *   }
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class Visit(
    val hierarchyLevel: Int,
    val probability: Double,
    val topCandidate: TopCandidate
)

/**
 * Represents a top candidate with place ID, semantic type, probability, and place location.
 *
 * Example JSON:
 * ```json
 * {
 *   "placeId": "ChIJd8BlQ2BZwokRAFUEcm_qrcA",
 *   "semanticType": "INFERRED_WORK",
 *   "probability": 0.95,
 *   "placeLocation": {
 *     "latLng": "40.712776°, -74.005974°"
 *   }
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class TopCandidate(
    val placeId: String,
    val semanticType: String,
    val probability: Double,
    val placeLocation: PlaceLocation
)

/**
 * Represents a place location with latitude and longitude.
 *
 * Example JSON:
 * ```json
 * {
 *   "latLng": "40.712776°, -74.005974°"
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class PlaceLocation(
    val latLng: String
)