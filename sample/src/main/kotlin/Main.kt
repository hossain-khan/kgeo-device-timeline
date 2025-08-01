package dev.hossain.timeline.sample

import dev.hossain.timeline.Parser
import java.io.File

/**
 * Main entry point for testing the parser.
 */
suspend fun main() {
    println("Hello Sample!")
    // Add the file in `sample/src/main/resources` directory
    val resource = {}.javaClass.getResource("/device-timeline.json")
    if (resource != null) {
        val file = File(resource.toURI())
        val timeline = Parser.parse(file)

        println("Parsed timeline data with ${timeline.semanticSegments.size} segments")
        println("Parsed timeline data with ${timeline.rawSignals.size} signals")
        println("Parsed timeline data with ${timeline.userLocationProfile.frequentPlaces.size} frequent places")

        val activityTypes: List<String> = timeline.rawSignals.mapNotNull { rawSignal ->
            rawSignal.activityRecord?.probableActivities?.map { it.type }
        }.flatten().distinct().sorted()
        println("Unique activities: $activityTypes")
    } else {
        println("The device timeline file not found in `sample/src/main/resources` directory.")
    }
}