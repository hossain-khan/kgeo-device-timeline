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
 * 
 * This parser uses Moshi for JSON deserialization and reuses a single Moshi instance
 * for optimal performance.
 */
class Parser {
    
    companion object {
        /**
         * Shared Moshi instance for optimal performance across all parser operations.
         */
        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
            
        /**
         * Shared adapter for TimelineData deserialization.
         */
        private val adapter = moshi.adapter(TimelineData::class.java)
    }
    /**
     * Parses the given file into a [TimelineData] object.
     *
     * @param file The JSON file of timeline data to be parsed.
     * @return The parsed [TimelineData] object.
     * @throws IllegalStateException if the file does not exist.
     * @throws com.squareup.moshi.JsonDataException if the JSON format is invalid.
     * @throws java.io.IOException if there's an error reading the file.
     */
    suspend fun parse(file: File): TimelineData = withContext(Dispatchers.IO) {
        if (!file.exists()) {
            throw IllegalStateException("File not found: ${file.absolutePath}")
        }
        val source: BufferedSource = file.source().buffer()
        return@withContext parse(source)
    }

    /**
     * Parses the given input stream into a [TimelineData] object.
     *
     * @param inputStream The input stream of JSON data to be parsed.
     * @return The parsed [TimelineData] object.
     * @throws com.squareup.moshi.JsonDataException if the JSON format is invalid.
     * @throws java.io.IOException if there's an error reading the input stream.
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
     * @throws com.squareup.moshi.JsonDataException if the JSON format is invalid.
     * @throws IllegalStateException if the data cannot be parsed (null result).
     * @throws java.io.IOException if there's an error reading the buffered source.
     */
    suspend fun parse(bufferedSource: BufferedSource): TimelineData = withContext(Dispatchers.IO) {
        val data = adapter.fromJson(bufferedSource)
        data ?: throw IllegalStateException("Failed to parse timeline data: JSON resulted in null object")
    }
}