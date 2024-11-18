package dev.hossain.timeline

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.hossain.timeline.model.TimelineData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.BufferedSource
import okio.buffer
import okio.source
import java.io.File
import java.io.InputStream

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
            val source: BufferedSource = file.source().buffer()
            return@withContext parse(source)
        } else {
            throw IllegalStateException("File not found")
        }
    }

    /**
     * Parses the given input stream into a [TimelineData] object.
     *
     * @param inputStream The input stream of JSON data to be parsed.
     * @return The parsed [TimelineData] object.
     * @throws IllegalStateException if the data cannot be parsed.
     */
    suspend fun parse(inputStream: InputStream): TimelineData = withContext(Dispatchers.IO) {
        val source: BufferedSource = inputStream.source().buffer()
        return@withContext parse(source)
    }

    /**
     * Parses the given buffered source into a [TimelineData] object.
     *
     * @param bufferedSource The buffered source of JSON data to be parsed.
     * @return The parsed [TimelineData] object.
     * @throws IllegalStateException if the data cannot be parsed.
     */
    suspend fun parse(bufferedSource: BufferedSource): TimelineData = withContext(Dispatchers.IO) {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val adapter = moshi.adapter(TimelineData::class.java)
        val data = adapter.fromJson(bufferedSource)

        data ?: throw IllegalStateException("Failed to parse data")
    }
}