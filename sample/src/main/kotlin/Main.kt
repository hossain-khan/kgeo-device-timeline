package dev.hossain.timeline.sample

import dev.hossain.timeline.Parser
import dev.hossain.timeline.ParseResult
import dev.hossain.timeline.ParserConfig
import java.io.File

/**
 * Main entry point for testing the parser.
 */
suspend fun main() {
    println("Hello Sample!")
    
    // Demo of new API design
    demoNewAPI()
    
    // Original functionality
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

suspend fun demoNewAPI() {
    println("\n=== Parser API Improvements Demo ===")
    
    // Example JSON for demo
    val jsonExample = """
    {
        "semanticSegments": [
            {
                "startTime": "2023-10-01T08:00:00.000Z",
                "endTime": "2023-10-01T09:00:00.000Z",
                "visit": null
            }
        ],
        "rawSignals": [],
        "userLocationProfile": {
            "frequentPlaces": []
        }
    }
    """.trimIndent()
    
    // 1. Result-based API
    println("\n1. Result-based API (recommended):")
    when (val result = Parser.fromJsonString(jsonExample)) {
        is ParseResult.Success -> {
            println("   ✓ Successfully parsed ${result.data.semanticSegments.size} segments")
        }
        is ParseResult.Error -> {
            println("   ✗ Error: ${result.message}")
        }
    }
    
    // 2. Performance configuration
    println("\n2. Performance-optimized parsing:")
    when (val result = Parser.fromJsonString(jsonExample, ParserConfig.PERFORMANCE)) {
        is ParseResult.Success -> {
            println("   ✓ Performance mode: ${result.data.semanticSegments.size} segments")
        }
        is ParseResult.Error -> {
            println("   ✗ Error: ${result.message}")
        }
    }
    
    // 3. Error handling
    println("\n3. Graceful error handling:")
    when (val result = Parser.fromJsonString("{ invalid json }")) {
        is ParseResult.Success -> {
            println("   ✗ This shouldn't succeed")
        }
        is ParseResult.Error -> {
            println("   ✓ Handled error: ${result.message}")
        }
    }
    
    println("=== Demo completed! ===\n")
}