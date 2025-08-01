package dev.hossain.timeline

import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonEncodingException
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.hossain.timeline.model.TimelineData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.BufferedSource
import okio.buffer
import okio.source
import java.io.File
import java.io.IOException
import java.io.InputStream

/**
 * An object responsible for parsing JSON data from various sources into a [TimelineData] object.
 * 
 * This parser uses Moshi for JSON deserialization and provides both traditional exception-based
 * and modern Result-based APIs for error handling. The parser is stateless and thread-safe.
 * 
 * Example usage:
 * ```kotlin
 * // Exception-based API (backward compatible)
 * val data = Parser.parse(file)
 * 
 * // Result-based API (recommended)
 * when (val result = Parser.parseToResult(file)) {
 *     is ParseResult.Success -> println("Parsed ${result.data.semanticSegments.size} segments")
 *     is ParseResult.Error -> println("Error: ${result.message}")
 * }
 * 
 * // With custom configuration
 * val data = Parser.parse(file, ParserConfig.PERFORMANCE)
 * ```
 */
object Parser {
    
    /**
     * Lazily initialized Moshi instance for optimal performance across all parser operations.
     */
    private val moshi by lazy {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
    /**
     * Lazily initialized adapter for TimelineData deserialization.
     */
    private val adapter by lazy {
        moshi.adapter(TimelineData::class.java)
    }
    
    // MARK: - Exception-based API (backward compatible)
    
    /**
     * Parses the given file into a [TimelineData] object using default configuration.
     *
     * @param file The JSON file of timeline data to be parsed.
     * @return The parsed [TimelineData] object.
     * @throws FileNotFoundException if the file does not exist.
     * @throws InvalidFormatException if the JSON format is invalid.
     * @throws ParseIOException if there's an error reading the file.
     */
    suspend fun parse(file: File): TimelineData = parse(file, ParserConfig.DEFAULT)
    
    /**
     * Parses the given file into a [TimelineData] object with custom configuration.
     *
     * @param file The JSON file of timeline data to be parsed.
     * @param config Configuration options for parsing.
     * @return The parsed [TimelineData] object.
     * @throws FileNotFoundException if the file does not exist.
     * @throws InvalidFormatException if the JSON format is invalid.
     * @throws ParseIOException if there's an error reading the file.
     */
    suspend fun parse(file: File, config: ParserConfig): TimelineData = withContext(Dispatchers.IO) {
        if (config.validateInput && !file.exists()) {
            throw FileNotFoundException(file.absolutePath)
        }
        
        try {
            val source: BufferedSource = file.source().buffer()
            return@withContext parse(source, config)
        } catch (e: IOException) {
            throw ParseIOException("Failed to read file: ${file.absolutePath}", e)
        }
    }
    }

    /**
     * Parses the given input stream into a [TimelineData] object using default configuration.
     *
     * @param inputStream The input stream of JSON data to be parsed.
     * @return The parsed [TimelineData] object.
     * @throws InvalidFormatException if the JSON format is invalid.
     * @throws ParseIOException if there's an error reading the input stream.
     */
    suspend fun parse(inputStream: InputStream): TimelineData = parse(inputStream, ParserConfig.DEFAULT)
    
    /**
     * Parses the given input stream into a [TimelineData] object with custom configuration.
     *
     * @param inputStream The input stream of JSON data to be parsed.
     * @param config Configuration options for parsing.
     * @return The parsed [TimelineData] object.
     * @throws InvalidFormatException if the JSON format is invalid.
     * @throws ParseIOException if there's an error reading the input stream.
     */
    suspend fun parse(inputStream: InputStream, config: ParserConfig): TimelineData = withContext(Dispatchers.IO) {
        try {
            val source: BufferedSource = inputStream.source().buffer()
            return@withContext parse(source, config)
        } catch (e: IOException) {
            throw ParseIOException("Failed to read input stream", e)
        }
    }

    /**
     * Parses the given buffered source into a [TimelineData] object using default configuration.
     *
     * @param bufferedSource The buffered source of JSON data to be parsed.
     * @return The parsed [TimelineData] object.
     * @throws InvalidFormatException if the JSON format is invalid.
     * @throws ParseIOException if there's an error reading the buffered source.
     */
    suspend fun parse(bufferedSource: BufferedSource): TimelineData = parse(bufferedSource, ParserConfig.DEFAULT)
    
    /**
     * Parses the given buffered source into a [TimelineData] object with custom configuration.
     *
     * @param bufferedSource The buffered source of JSON data to be parsed.
     * @param config Configuration options for parsing.
     * @return The parsed [TimelineData] object.
     * @throws InvalidFormatException if the JSON format is invalid.
     * @throws ParseIOException if there's an error reading the buffered source.
     */
    suspend fun parse(bufferedSource: BufferedSource, config: ParserConfig): TimelineData = withContext(Dispatchers.IO) {
        try {
            val data = adapter.fromJson(bufferedSource)
            return@withContext data ?: throw InvalidFormatException("JSON resulted in null object")
        } catch (e: JsonDataException) {
            throw InvalidFormatException("Invalid JSON structure", e)
        } catch (e: JsonEncodingException) {
            throw InvalidFormatException("Invalid JSON encoding", e)
        } catch (e: IOException) {
            throw ParseIOException("IO error during JSON parsing", e)
        }
    }
    }
}
