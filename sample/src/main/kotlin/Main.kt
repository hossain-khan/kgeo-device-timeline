package dev.hossain.timeline.sample

import dev.hossain.timeline.Parser
import java.io.File

fun main() {
    println("Hello Sample!")
    val parser = Parser()
    val resource = {}.javaClass.getResource("/data.json")
    if (resource != null) {
        val file = File(resource.toURI())
        parser.parse(file)
    } else {
        println("Resource not found")
    }
}