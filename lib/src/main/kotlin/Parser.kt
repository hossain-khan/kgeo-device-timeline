package dev.hossain.timeline

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.hossain.timeline.model.TimelineData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.buffer
import okio.source
import java.io.File

/**
 * A class responsible for parsing JSON data from a file into a [TimelineData] object.
 */
class Parser {
    /**
     * Parses the given file into a [TimelineData] object.
     *
     * @param file The JSON file of timeline data to be parsed.
     * @return The parsed [TimelineData] object.
     * @throws IllegalStateException if the file does not exist or if the data cannot be parsed.
     */
    suspend fun parse(file: File): TimelineData = withContext(Dispatchers.IO) {
        if (file.exists()) {
            val source = file.source().buffer()
            val json = source.readUtf8()

            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            val adapter = moshi.adapter(TimelineData::class.java)
            val data = adapter.fromJson(json)

            data ?: throw IllegalStateException("Failed to parse data")
        } else {
            throw IllegalStateException("File not found")
        }
    }
}