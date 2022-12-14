package se.phan.redacted.parser

import se.phan.redacted.text.Newline
import se.phan.redacted.text.Punctuation
import se.phan.redacted.text.Space
import se.phan.redacted.text.Word
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TextParserTest {

    @Test
    fun `parse empty string`() {
        val text = TextParser.parse("")

        assertTrue(text.parts.isEmpty())
    }

    @Test
    fun `parse one word`() {
        val str = "Dune"
        val text = TextParser.parse(str)

        assertEquals(
            expected = listOf(Word(str)),
            actual = text.parts
        )
    }

    @Test
    fun `parse two words`() {
        val str = "Paul Atreides"
        val text = TextParser.parse(str)

        assertEquals(
            expected = listOf(
                Word("Paul"),
                Space,
                Word("Atreides")
            ),
            actual = text.parts
        )
    }

    @Test
    fun `parse punctuation`() {
        for (punctuation in SpecialCharacters.PUNCTUATION) {
            testPunctuationMark(punctuation)
        }
    }

    @Test
    fun `parse space after punctuation`() {
        val str = "Paul, Atreides"
        val text = TextParser.parse(str)

        assertEquals(
            expected = listOf(
                Word("Paul"),
                Punctuation(','),
                Space,
                Word("Atreides")
            ),
            actual = text.parts
        )
    }

    @Test
    fun `parse newline`() {
        val str = """
            Paul
            
            Atreides
        """.trimIndent()

        val text = TextParser.parse(str)

        assertEquals(
            expected = listOf(
                Word("Paul"),
                Newline,
                Newline,
                Word("Atreides")
            ),
            actual = text.parts
        )
    }

    private fun testPunctuationMark(punctuationMark: Char) {
        val str = "Paul Atreides$punctuationMark"
        val text = TextParser.parse(str)

        assertEquals(
            expected = listOf(
                Word("Paul"),
                Space,
                Word("Atreides"),
                Punctuation(punctuationMark)
            ),
            actual = text.parts
        )
    }
}