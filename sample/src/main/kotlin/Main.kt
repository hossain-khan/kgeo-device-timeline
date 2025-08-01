package dev.hossain.timeline.sample

import dev.hossain.timeline.Parser
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
    
    val parser = Parser()
    
    // Look for sample data file in resources directory
    val resource = {}.javaClass.getResource("/device-timeline.json")
    
    if (resource != null) {
        try {
            val file = File(resource.toURI())
            println("📁 Parsing timeline data from: ${file.name}")
            
            val timeline = parser.parse(file)

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