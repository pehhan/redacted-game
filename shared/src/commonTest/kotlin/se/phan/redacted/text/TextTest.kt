package se.phan.redacted.text

import se.phan.redacted.guess.Guess
import se.phan.redacted.guess.WordAlreadyRevealed
import se.phan.redacted.guess.WordNotInText
import se.phan.redacted.guess.WordRevealed
import se.phan.redacted.parser.TextParser
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.fail

class TextTest {

    @Test
    fun `when guessing a word that is not in text - the result should be WordNotInText`() {
        val str = "Paul Atreides"
        val text = TextParser.parse(str)

        val result = text.makeGuess(Guess("Dune"))

        assertTrue(result is WordNotInText)
    }

    @Test
    fun `when guessing a word that was already revealed - the result should be WordAlreadyRevealed`() {
        val str = "Paul Atreides"
        val text = TextParser.parse(str)
        val guess = Guess("Paul")

        val result = text.makeGuess(guess)

        if (result is WordRevealed) {
            val secondResult = result.text.makeGuess(guess)
            assertTrue(secondResult is WordAlreadyRevealed)
        } else {
            fail()
        }
    }

    @Test
    fun `when guessing a word that is in the text once - a new text with 1 matches should be returned`() {
        val str = "Paul Atreides"
        val text = TextParser.parse(str)

        val result = text.makeGuess(Guess("Paul"))

        val expectedText = Text(
            listOf(
                Word("Paul", redacted = false),
                Space,
                Word("Atreides", redacted = true)
            )
        )

        if (result is WordRevealed) {
            assertEquals(expected = expectedText, actual = result.text)
            assertEquals(expected = 1, actual = result.matches)
        } else {
            fail()
        }
    }

    @Test
    fun `when guessing a word that is in the text twice - a new text with 2 matches should be returned`() {
        val str = "Paul Atreides and Leto Atreides"
        val text = TextParser.parse(str)

        val result = text.makeGuess(Guess("Atreides"))

        val expectedText = Text(
            listOf(
                Word("Paul", redacted = true),
                Space,
                Word("Atreides", redacted = false),
                Space,
                Word("and", redacted = true),
                Space,
                Word("Leto", redacted = true),
                Space,
                Word("Atreides", redacted = false)
            )
        )

        if (result is WordRevealed) {
            assertEquals(expected = expectedText, actual = result.text)
            assertEquals(expected = 2, actual = result.matches)
        } else {
            fail()
        }
    }

    @Test
    fun `when guessing a word in lowercase which is in uppercase in the text it should match`() {
        testDifferentCaps("ATREIDES", "atreides")
    }

    @Test
    fun `when guessing a word in uppercase which is in lowercase in the text it should match`() {
        testDifferentCaps("atreides", "ATREIDES")
    }

    @Test
    fun `when guessing a word in mixed case which is in uppercase in the text it should match`() {
        testDifferentCaps("ATREIDES", "AtreiDes")
    }

    @Test
    fun `when guessing a word in mixed case which is in lowercase in the text it should match`() {
        testDifferentCaps("atreides", "AtreiDes")
    }

    @Test
    fun `when guessing a word in lowercase which is in mixed case in the text it should match`() {
        testDifferentCaps("AtreiDes", "atreides")
    }

    @Test
    fun `when guessing a word in uppercase which is in mixed case in the text it should match`() {
        testDifferentCaps("AtreiDes", "ATREIDES")
    }

    @Test
    fun `when guessing a word in mixed case which is in different mixed case in the text it should match`() {
        testDifferentCaps("ATreidES", "AtreiDes")
    }

    @Test
    fun `when guessing a number it should match`() {
        testDifferentCaps("1965", "1965")
    }

    @Test
    fun `when guessing a mix of characters and numbers it should match`() {
        testDifferentCaps("20th", "20th")
    }

    @Test
    fun `when guessing a mix of characters in lowercase and numbers it should match`() {
        testDifferentCaps("20TH", "20th")
    }

    @Test
    fun `when guessing a mix of characters in uppercase and numbers it should match`() {
        testDifferentCaps("20th", "20TH")
    }

    @Test
    fun `when guessing with whitespace before and after ist should match`() {
        testDifferentCaps("Atreides", " Atreides ")
    }

    @Test
    fun `areAllWordsRevealed returns true when all words in text are revealed`() {
        val parts = listOf(
            Word("Paul", redacted = false),
            Space,
            Word("Atreides", redacted = false)
        )
        val text = Text(parts)

        assertTrue(text.areAllWordsRevealed())
    }

    @Test
    fun `areAllWordsRevealed returns false when some words in text is not revealed`() {
        val parts = listOf(
            Word("Paul", redacted = false),
            Space,
            Word("Atreides", redacted = true)
        )
        val text = Text(parts)

        assertFalse(text.areAllWordsRevealed())
    }

    @Test
    fun `revealAll reveals all words in text`() {
        val parts = listOf(
            Word("Paul", redacted = true),
            Space,
            Word("Atreides", redacted = true)
        )
        val text = Text(parts)

        val revealedText = text.revealAll()

        val expectedText = Text(
            listOf(
                Word("Paul", redacted = false),
                Space,
                Word("Atreides", redacted = false)
            )
        )

        assertEquals(expected = expectedText, actual = revealedText)
    }

    @Test
    fun `revealWords reveals all matching words in text`() {
        val parts = listOf(
            Word("Dune"),
            Space,
            Word("is"),
            Space,
            Word("a"),
            Space,
            Word("book"),
            Punctuation('.'),
            Space,
            Word("It"),
            Space,
            Word("is"),
            Space,
            Word("good"),
            Punctuation('.')
        )
        val text = Text(parts)

        val wordsToReveal = listOf(
            Word("a"),
            Word("is"),
            Word("it")
        )

        val revealedText = text.revealWords(wordsToReveal)

        val expectedText = Text(
            listOf(
                Word("Dune"),
                Space,
                Word("is", redacted = false),
                Space,
                Word("a", redacted = false),
                Space,
                Word("book"),
                Punctuation('.'),
                Space,
                Word("It", redacted = false),
                Space,
                Word("is", redacted = false),
                Space,
                Word("good"),
                Punctuation('.')
            )
        )

        assertEquals(expected = expectedText, actual = revealedText)
    }

    @Test
    fun `should be able to reveal a list of words in the text`() {
        val parts = listOf(
            Word("Dune"),
            Space,
            Word("is"),
            Space,
            Word("a"),
            Space,
            Word("book"),
            Punctuation('.')
        )
        val text = Text(parts)

        val revealedText = text.revealWords(listOf("is", "a").map { Word(it) })

        val expectedText = Text(
            listOf(
                Word("Dune"),
                Space,
                Word("is", redacted = false),
                Space,
                Word("a", redacted = false),
                Space,
                Word("book"),
                Punctuation('.')
            )
        )

        assertEquals(expected = expectedText, actual = revealedText)
    }

    private fun testDifferentCaps(wordInText: String, guess: String) {
        val str = "Paul $wordInText"
        val text = TextParser.parse(str)

        val result = text.makeGuess(Guess(guess))

        val expectedText = Text(
            listOf(
                Word("Paul", redacted = true),
                Space,
                Word(wordInText, redacted = false)
            )
        )

        if (result is WordRevealed) {
            assertEquals(expected = expectedText, actual = result.text)
            assertEquals(expected = 1, actual = result.matches)
        } else {
            fail()
        }
    }
}