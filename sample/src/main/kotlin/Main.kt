package dev.hossain.timeline.sample

import dev.hossain.timeline.Parser
import java.io.File

suspend fun main() {
    println("Hello Sample!")
    val parser = Parser()
    val resource = {}.javaClass.getResource("/device-timeline.json")
    if (resource != null) {
        val file = File(resource.toURI())
        val timeline = parser.parse(file)

        println("Parsed timeline data with ${timeline.semanticSegments.size} segments")
        println("Parsed timeline data with ${timeline.rawSignals.size} signals")
        println("Parsed timeline data with ${timeline.userLocationProfile.frequentPlaces.size} frequent places")

        val activityTypes: List<String> = timeline.rawSignals.map { it.activityRecord?.probableActivities?.map { it.type} ?: emptyList() }.flatten().distinct().sorted()
        println("Unique activities: $activityTypes")
    } else {
        println("Resource not found")
    }
}