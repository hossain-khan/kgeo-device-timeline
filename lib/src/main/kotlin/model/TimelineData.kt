package dev.hossain.timeline.model

import com.squareup.moshi.JsonClass

/**
 * Data class representing the timeline data.
 *
 * @property semanticSegments List of semantic segments in the timeline.
 * @property rawSignals List of raw signals in the timeline.
 * @property userLocationProfile User location profile containing frequent places.
 */
@JsonClass(generateAdapter = true)
data class TimelineData(
    val semanticSegments: List<SemanticSegment>,
    val rawSignals: List<RawSignal>,
    val userLocationProfile: UserLocationProfile
)