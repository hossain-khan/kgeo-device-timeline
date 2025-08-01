package dev.hossain.timeline.sample

import dev.hossain.timeline.Parser
import dev.hossain.timeline.ParseResult
import dev.hossain.timeline.ParserConfig
import java.io.File

/**
 * Sample application demonstrating usage of the Google Device Timeline JSON Parser.
 * 
 * This example shows how to:
 * - Parse timeline data from a JSON file
 * - Access different types of timeline information
 * - Extract and analyze activity data
 */
suspend fun main() {
    println("🚀 Google Device Timeline JSON Parser - Sample Application")
    println("=" .repeat(60))
    
    // Demo of new API design
    demoNewAPI()
    
    // Look for sample data file in resources directory
    val resource = {}.javaClass.getResource("/device-timeline.json")
    
    if (resource != null) {
        try {
            val file = File(resource.toURI())
            println("📁 Parsing timeline data from: ${file.name}")
            
            val timeline = Parser.parse(file)

            // Display basic statistics
            println("\n📊 Timeline Data Summary:")
            println("  • Semantic segments: ${timeline.semanticSegments.size}")
            println("  • Raw signals: ${timeline.rawSignals.size}")
            println("  • Frequent places: ${timeline.userLocationProfile.frequentPlaces.size}")

            // Analyze activity types
            val activityTypes = timeline.rawSignals
                .mapNotNull { it.activityRecord?.probableActivities }
                .flatten()
                .map { it.type }
                .distinct()
                .sorted()
                
            if (activityTypes.isNotEmpty()) {
                println("\n🏃 Detected Activities:")
                activityTypes.forEach { activity ->
                    println("  • $activity")
                }
            }
            
            // Show sample places if available
            val frequentPlaces = timeline.userLocationProfile.frequentPlaces.take(3)
            if (frequentPlaces.isNotEmpty()) {
                println("\n📍 Sample Frequent Places:")
                frequentPlaces.forEach { place ->
                    val location = place.placeLocation.ifEmpty { "Unknown location" }
                    val label = place.label.ifEmpty { "Unlabeled" }
                    println("  • $location ($label)")
                }
            }
            
            println("\n✅ Parsing completed successfully!")
            
        } catch (e: Exception) {
            println("❌ Error parsing timeline data: ${e.message}")
        }
    } else {
        println("📄 No sample timeline data found.")
        println("To test with your own data:")
        println("  1. Export your Google Location History Timeline data")
        println("  2. Place the JSON file in: sample/src/main/resources/device-timeline.json")
        println("  3. Run this sample again")
        println("\nFor more information, see: https://github.com/hossain-khan/kgeo-device-timeline")
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