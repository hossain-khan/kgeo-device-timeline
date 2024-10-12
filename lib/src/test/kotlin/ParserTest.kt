import dev.hossain.timeline.Parser
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

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
    fun parse() {
        val resource = {}.javaClass.getResource("/test-data.json")
        val file = File(resource?.toURI() ?: throw IllegalStateException("Resource not found"))
        val timeline = parser.parse(file)

        assertNotNull(timeline)
        assertEquals(2, timeline.semanticSegments.size)
        assertEquals(2, timeline.rawSignals.size)
        assertEquals(5, timeline.userLocationProfile.frequentPlaces.size)
    }
}