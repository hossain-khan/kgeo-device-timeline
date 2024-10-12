package dev.hossain.timeline.sample

import dev.hossain.timeline.Parser
import java.io.File

fun main() {
    println("Hello Sample!")
    val parser = Parser()
    val resource = {}.javaClass.getResource("/device-timeline.json")
    if (resource != null) {
        val file = File(resource.toURI())
        val timeline = parser.parse(file)

        println("Parsed timeline data with ${timeline.semanticSegments.size} segments")
        println("Parsed timeline data with ${timeline.rawSignals.size} signals")
        println("Parsed timeline data with ${timeline.userLocationProfile.frequentPlaces.size} frequent places")
    } else {
        println("Resource not found")
    }
}