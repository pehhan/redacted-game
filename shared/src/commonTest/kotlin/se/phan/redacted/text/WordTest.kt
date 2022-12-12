package se.phan.redacted.text

import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import se.phan.redacted.guess.Guess
import kotlin.test.Test

class WordTest {

    @Test
    fun `guess matches word`() {
        expect(Word("Atreides").matches(Guess("Atreides"))).toEqual(true)
    }

    @Test
    fun `wrong guess does not match word`() {
        expect(Word("Atreides").matches(Guess("Paul"))).toEqual(false)
    }

    @Test
    fun `guess in lowercase matches uppercase word`() {
        expect(Word("ATREIDES").matches(Guess("atreides"))).toEqual(true)
    }

    @Test
    fun `guessing in uppercase matches lowercase word`() {
        expect(Word("atreides").matches(Guess("ATREIDES"))).toEqual(true)
    }

    @Test
    fun `guess in mixed case matches uppercase word`() {
        expect(Word("ATREIDES").matches(Guess("AtreiDes"))).toEqual(true)
    }

    @Test
    fun `guess in mixed case matches lowercase word`() {
        expect(Word("atreides").matches(Guess("AtreiDes"))).toEqual(true)
    }

    @Test
    fun `guess in lowercase matches mixed case word`() {
        expect(Word("AtreiDes").matches(Guess("atreides"))).toEqual(true)
    }

    @Test
    fun `guess in uppercase matches mixed case word`() {
        expect(Word("AtreiDes").matches(Guess("ATREIDES"))).toEqual(true)
    }

    @Test
    fun `guess in mixed case matches different mixed case word`() {
        expect(Word("ATreidES").matches(Guess("AtreiDes"))).toEqual(true)
    }

    @Test
    fun `guess in integers matches word`() {
        expect(Word("1965").matches(Guess("1965"))).toEqual(true)
    }

    @Test
    fun `guess in a mix of characters and integers matches word`() {
        expect(Word("20th").matches(Guess("20th"))).toEqual(true)
    }

    @Test
    fun `guess in a mix of characters in lowercase and integers matches word`() {
        expect(Word("20TH").matches(Guess("20th"))).toEqual(true)
    }

    @Test
    fun `guess in a mix of characters in uppercase and integers matches word`() {
        expect(Word("20th").matches(Guess("20TH"))).toEqual(true)
    }

    @Test
    fun `word matches word`() {
        expect(Word("Atreides").matches(Word("Atreides"))).toEqual(true)
    }

    @Test
    fun `other word does not match word`() {
        expect(Word("Atreides").matches(Word("Paul"))).toEqual(false)
    }

    @Test
    fun `word in lowercase matches uppercase word`() {
        expect(Word("ATREIDES").matches(Word("atreides"))).toEqual(true)
    }

    @Test
    fun `word in uppercase matches lowercase word`() {
        expect(Word("atreides").matches(Word("ATREIDES"))).toEqual(true)
    }

    @Test
    fun `word in mixed case matches uppercase word`() {
        expect(Word("ATREIDES").matches(Word("AtreiDes"))).toEqual(true)
    }

    @Test
    fun `word in mixed case matches lowercase word`() {
        expect(Word("atreides").matches(Word("AtreiDes"))).toEqual(true)
    }

    @Test
    fun `word in lowercase matches mixed case word`() {
        expect(Word("AtreiDes").matches(Word("atreides"))).toEqual(true)
    }

    @Test
    fun `word in uppercase matches mixed case word`() {
        expect(Word("AtreiDes").matches(Word("ATREIDES"))).toEqual(true)
    }

    @Test
    fun `word in mixed case matches different mixed case word`() {
        expect(Word("ATreidES").matches(Word("AtreiDes"))).toEqual(true)
    }

    @Test
    fun `word in integers matches word`() {
        expect(Word("1965").matches(Word("1965"))).toEqual(true)
    }

    @Test
    fun `word in a mix of characters and integers matches word`() {
        expect(Word("20th").matches(Word("20th"))).toEqual(true)
    }

    @Test
    fun `word in a mix of characters in lowercase and integers matches word`() {
        expect(Word("20TH").matches(Word("20th"))).toEqual(true)
    }

    @Test
    fun `word in a mix of characters in uppercase and integers matches word`() {
        expect(Word("20th").matches(Word("20TH"))).toEqual(true)
    }

    @Test
    fun `word with accent matches word without accent`() {
        expect(Word("åäöáè").matches(Word("aaoae"))).toEqual(true)
        expect(Word("aaoae").matches(Word("åäöáè"))).toEqual(true)
    }
}