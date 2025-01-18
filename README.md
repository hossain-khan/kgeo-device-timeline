# Google Device Timeline JSON Parser

Parses Google's Location History Timeline JSON data exported from device.

![Device Timeline Export Flow](resources/device-export-flow/device-timeline-export-flow.png)

### Usage

```kotlin
val parser = Parser()
val file = File("timeline.json")
val timeline = parser.parse(file)

println("Parsed timeline data with ${timeline.semanticSegments.size} segments")
println("Parsed timeline data with ${timeline.rawSignals.size} signals")
println("Parsed timeline data with ${timeline.userLocationProfile.frequentPlaces.size} frequent places")
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
