package dev.hossain.timeline.model

import com.squareup.moshi.JsonClass

/**
 * Represents a user location profile with a list of frequent places.
 *
 * Example JSON:
 * ```json
 * {
 *   "frequentPlaces": [
 *     {
 *       "placeId": "ChIJd8BlQ2BZwokRAFUEcm_qrcA",
 *       "placeLocation": "40.712776°, -74.005974°",
 *       "label": "WORK"
 *     },
 *     {
 *       "placeId": "ChIJd8BlQ2BZwokRAFUEcm_qrcB",
 *       "placeLocation": "40.713776°, -74.006974°",
 *       "label": "HOME"
 *     }
 *   ]
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class UserLocationProfile(
    val frequentPlaces: List<FrequentPlace>
)

/**
 * Represents a frequent place with place ID, place location, and label.
 *
 * Example JSON:
 * ```json
 * {
 *   "placeId": "ChIJd8BlQ2BZwokRAFUEcm_qrcA",
 *   "placeLocation": "40.712776°, -74.005974°",
 *   "label": "WORK"
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class FrequentPlace(
    val placeId: String,
    val placeLocation: String,
    val label: String
)
