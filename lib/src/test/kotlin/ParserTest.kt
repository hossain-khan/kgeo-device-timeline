import dev.hossain.timeline.Parser
import kotlinx.coroutines.test.runTest
import okio.buffer
import okio.source
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File
import java.io.InputStream

/**
 * Test for [Parser]
 */
class ParserTest {

    private lateinit var parser: Parser

    @BeforeEach
    fun setUp() {
        parser = Parser()
    }

    @Test
    fun parseFile() = runTest {
        val resource = {}.javaClass.getResource("/test-data.json")
        val file = File(resource?.toURI() ?: throw IllegalStateException("Resource not found"))
        val timeline = parser.parse(file)

        assertNotNull(timeline)
        assertEquals(2, timeline.semanticSegments.size)
        assertEquals(2, timeline.rawSignals.size)
        assertEquals(5, timeline.userLocationProfile.frequentPlaces.size)
    }

    @Test
    fun parseInputStream() = runTest {
        val resource = {}.javaClass.getResourceAsStream("/test-data.json")
        val inputStream: InputStream = resource ?: throw IllegalStateException("Resource not found")
        val timeline = parser.parse(inputStream)

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
        val timeline = parser.parse(bufferedSource)

        assertNotNull(timeline)
        assertEquals(2, timeline.semanticSegments.size)
        assertEquals(2, timeline.rawSignals.size)
        assertEquals(5, timeline.userLocationProfile.frequentPlaces.size)
    }
}