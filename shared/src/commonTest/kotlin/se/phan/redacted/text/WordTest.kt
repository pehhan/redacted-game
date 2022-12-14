package se.phan.redacted.text

import se.phan.redacted.guess.Guess
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class WordTest {

    @Test
    fun `guess matches word`() {
        assertTrue(Word("Atreides").matches(Guess("Atreides")))
    }

    @Test
    fun `wrong guess does not match word`() {
        assertFalse(Word("Atreides").matches(Guess("Paul")))
    }

    @Test
    fun `guess in lowercase matches uppercase word`() {
        assertTrue(Word("ATREIDES").matches(Guess("atreides")))
    }

    @Test
    fun `guessing in uppercase matches lowercase word`() {
        assertTrue(Word("atreides").matches(Guess("ATREIDES")))
    }

    @Test
    fun `guess in mixed case matches uppercase word`() {
        assertTrue(Word("ATREIDES").matches(Guess("AtreiDes")))
    }

    @Test
    fun `guess in mixed case matches lowercase word`() {
        assertTrue(Word("atreides").matches(Guess("AtreiDes")))
    }

    @Test
    fun `guess in lowercase matches mixed case word`() {
        assertTrue(Word("AtreiDes").matches(Guess("atreides")))
    }

    @Test
    fun `guess in uppercase matches mixed case word`() {
        assertTrue(Word("AtreiDes").matches(Guess("ATREIDES")))
    }

    @Test
    fun `guess in mixed case matches different mixed case word`() {
        assertTrue(Word("ATreidES").matches(Guess("AtreiDes")))
    }

    @Test
    fun `guess in integers matches word`() {
        assertTrue(Word("1965").matches(Guess("1965")))
    }

    @Test
    fun `guess in a mix of characters and integers matches word`() {
        assertTrue(Word("20th").matches(Guess("20th")))
    }

    @Test
    fun `guess in a mix of characters in lowercase and integers matches word`() {
        assertTrue(Word("20TH").matches(Guess("20th")))
    }

    @Test
    fun `guess in a mix of characters in uppercase and integers matches word`() {
        assertTrue(Word("20th").matches(Guess("20TH")))
    }

    @Test
    fun `word matches word`() {
        assertTrue(Word("Atreides").matches(Word("Atreides")))
    }

    @Test
    fun `other word does not match word`() {
        assertFalse(Word("Atreides").matches(Word("Paul")))
    }

    @Test
    fun `word in lowercase matches uppercase word`() {
        assertTrue(Word("ATREIDES").matches(Word("atreides")))
    }

    @Test
    fun `word in uppercase matches lowercase word`() {
        assertTrue(Word("atreides").matches(Word("ATREIDES")))
    }

    @Test
    fun `word in mixed case matches uppercase word`() {
        assertTrue(Word("ATREIDES").matches(Word("AtreiDes")))
    }

    @Test
    fun `word in mixed case matches lowercase word`() {
        assertTrue(Word("atreides").matches(Word("AtreiDes")))
    }

    @Test
    fun `word in lowercase matches mixed case word`() {
        assertTrue(Word("AtreiDes").matches(Word("atreides")))
    }

    @Test
    fun `word in uppercase matches mixed case word`() {
        assertTrue(Word("AtreiDes").matches(Word("ATREIDES")))
    }

    @Test
    fun `word in mixed case matches different mixed case word`() {
        assertTrue(Word("ATreidES").matches(Word("AtreiDes")))
    }

    @Test
    fun `word in integers matches word`() {
        assertTrue(Word("1965").matches(Word("1965")))
    }

    @Test
    fun `word in a mix of characters and integers matches word`() {
        assertTrue(Word("20th").matches(Word("20th")))
    }

    @Test
    fun `word in a mix of characters in lowercase and integers matches word`() {
        assertTrue(Word("20TH").matches(Word("20th")))
    }

    @Test
    fun `word in a mix of characters in uppercase and integers matches word`() {
        assertTrue(Word("20th").matches(Word("20TH")))
    }

    @Test
    fun `word with accent matches word without accent`() {
        assertTrue(Word("åäöáè").matches(Word("aaoae")))
    }

    @Test
    fun `word without accent matches word with accent`() {
        assertTrue(Word("aaoae").matches(Word("åäöáè")))
    }

    @Test
    fun `word with accent matches guess without accent`() {
        assertTrue(Word("åäöáè").matches(Guess("aaoae")))
    }

    @Test
    fun `word without accent matches guess with accent`() {
        assertTrue(Word("aaoae").matches(Guess("åäöáè")))
    }
}