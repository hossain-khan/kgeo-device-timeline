# Google Device Timeline JSON Parser

Parses Google's Location History Timeline JSON data exported from device.

![Device Timeline Export Flow](resources/device-export-flow/device-timeline-export-flow.png)

### Usage
Get the latest **`.jar`** file containing the `Parser` from [releases](https://github.com/hossain-khan/kgeo-device-timeline/releases).

#### Basic Usage (Exception-based API)
```kotlin
val file = File("timeline.json")
val timeline = Parser.parse(file)

println("Parsed timeline data with ${timeline.semanticSegments.size} segments")
println("Parsed timeline data with ${timeline.rawSignals.size} signals")
println("Parsed timeline data with ${timeline.userLocationProfile.frequentPlaces.size} frequent places")
```

#### Recommended Usage (Result-based API)
```kotlin
val file = File("timeline.json")
when (val result = Parser.parseToResult(file)) {
    is ParseResult.Success -> {
        val timeline = result.data
        println("Parsed timeline data with ${timeline.semanticSegments.size} segments")
        println("Parsed timeline data with ${timeline.rawSignals.size} signals")
        println("Parsed timeline data with ${timeline.userLocationProfile.frequentPlaces.size} frequent places")
    }
    is ParseResult.Error -> {
        println("Error parsing timeline: ${result.message}")
    }
}
```

#### Advanced Usage with Configuration
```kotlin
val config = ParserConfig(
    enableLazyLoading = true,
    validateInput = false  // For better performance on trusted input
)
val timeline = Parser.parse(file, config)
```

#### Convenient Factory Methods
```kotlin
// Parse from file path
val result = Parser.fromFile("/path/to/timeline.json")

// Parse from JSON string
val jsonString = """{"semanticSegments": [], "rawSignals": [], "userLocationProfile": {"frequentPlaces": []}}"""
val result = Parser.fromJsonString(jsonString)
```


#### Output

```
Parsed timeline data with 51902 segments
Parsed timeline data with 7865 signals
Parsed timeline data with 2 frequent places
```


### Related Resources
* https://github.com/hossain-khan/kgeo-timeline
* https://github.com/CarlosBergillos/LocationHistoryFormat/issues/13#issuecomment-2370748731
