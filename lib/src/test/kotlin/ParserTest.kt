import dev.hossain.timeline.Parser
import kotlinx.coroutines.test.runTest
import okio.buffer
import okio.source
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import java.io.File
import java.io.InputStream

/**
 * Test for [Parser]
 */
class ParserTest {

    @Test
    fun parseFile() = runTest {
        val resource = {}.javaClass.getResource("/test-data.json")
        val file = File(resource?.toURI() ?: throw IllegalStateException("Resource not found"))
        val timeline = Parser.parse(file)

        assertNotNull(timeline)
        assertEquals(2, timeline.semanticSegments.size)
        assertEquals(2, timeline.rawSignals.size)
        assertEquals(5, timeline.userLocationProfile.frequentPlaces.size)
    }

    @Test
    fun parseInputStream() = runTest {
        val resource = {}.javaClass.getResourceAsStream("/test-data.json")
        val inputStream: InputStream = resource ?: throw IllegalStateException("Resource not found")
        val timeline = Parser.parse(inputStream)

        assertNotNull(timeline)
        assertEquals(2, timeline.semanticSegments.size)
        assertEquals(2, timeline.rawSignals.size)
        assertEquals(5, timeline.userLocationProfile.frequentPlaces.size)
    }


    @Test
    fun parseBufferedSource() = runTest {
        val resource = {}.javaClass.getResourceAsStream("/test-data.json")
        val inputStream: InputStream = resource ?: throw IllegalStateException("Resource not found")
        val bufferedSource = inputStream.source().buffer()
        val timeline = Parser.parse(bufferedSource)

        assertNotNull(timeline)
        assertEquals(2, timeline.semanticSegments.size)
        assertEquals(2, timeline.rawSignals.size)
        assertEquals(5, timeline.userLocationProfile.frequentPlaces.size)
    }

    @Test
    fun parseFileNotFound() = runTest {
        val nonExistentFile = File("non-existent-file.json")
        val exception = assertThrows<dev.hossain.timeline.FileNotFoundException> {
            Parser.parse(nonExistentFile)
        }
        assertTrue(exception.message?.contains("File not found") == true)
        assertTrue(exception.message?.contains(nonExistentFile.absolutePath) == true)
    }

    @Test
    fun parseInvalidJson() = runTest {
        val invalidJsonStream = "{ invalid json }".byteInputStream()
        assertThrows<dev.hossain.timeline.InvalidFormatException> {
            Parser.parse(invalidJsonStream)
        }
    }

    @Test
    fun parseToResultSuccess() = runTest {
        val resource = {}.javaClass.getResource("/test-data.json")
        val file = File(resource?.toURI() ?: throw IllegalStateException("Resource not found"))
        val result = Parser.parseToResult(file)

        assertTrue(result.isSuccess())
        assertNotNull(result.getOrNull())
        assertEquals(2, result.getOrThrow().semanticSegments.size)
    }

    @Test
    fun parseToResultError() = runTest {
        val nonExistentFile = File("non-existent-file.json")
        val result = Parser.parseToResult(nonExistentFile)

        assertTrue(result.isError())
        assertNull(result.getOrNull())
    }

    @Test
    fun fromFileMethod() = runTest {
        val resource = {}.javaClass.getResource("/test-data.json")
        val file = File(resource?.toURI() ?: throw IllegalStateException("Resource not found"))
        val result = Parser.fromFile(file.absolutePath)

        assertTrue(result.isSuccess())
        assertEquals(2, result.getOrThrow().semanticSegments.size)
    }
}