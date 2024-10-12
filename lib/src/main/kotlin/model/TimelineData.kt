package dev.hossain.timeline.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TimelineData(
    val semanticSegments: List<SemanticSegment>,
    val rawSignals: List<RawSignal>,
    val userLocationProfile: UserLocationProfile
)