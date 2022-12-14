package se.phan.redacted

import se.phan.redacted.guess.Guess
import se.phan.redacted.guess.GuessWithMatches
import se.phan.redacted.parser.TextParser
import se.phan.redacted.text.Punctuation
import se.phan.redacted.text.Space
import se.phan.redacted.text.Text
import se.phan.redacted.text.Word
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class GameTest {

    @Test
    fun `when creating a game the number of guesses should be zero and game in progress`() {
        val text = TextParser.parse("Paul Atreides")
        val game = Game(text, text)

        assertTrue(game.guesses.isEmpty())
        assertEquals(expected = text, actual = game.text)
        assertEquals(expected = text, actual = game.title)
        assertFalse(game.isCompleted())
    }

    @Test
    fun `when making a correct guess the word should be revealed`() {
        val text = TextParser.parse("Paul Atreides")
        val originalGame = Game(text, text)
        val guess = Guess("Paul")

        val gameWithGuess = originalGame.makeGuess(guess)

        val expectedText = Text(
            listOf(
                Word("Paul", redacted = false),
                Space,
                Word("Atreides", redacted = true)
            )
        )

        val expectedGuesses = listOf(GuessWithMatches(Guess(guess.value.lowercase()), 2))

        assertEquals(expected = expectedGuesses, actual = gameWithGuess.guesses)
        assertEquals(expected = expectedText, actual = gameWithGuess.text)
        assertEquals(expected = expectedText, actual = gameWithGuess.title)
        assertFalse(gameWithGuess.isCompleted())
    }

    @Test
    fun `when making a correct guess that has whitespace before and after the word should be revealed`() {
        val text = TextParser.parse("Paul Atreides")
        val originalGame = Game(text, text)
        val guess = Guess(" Paul ")

        val gameWithGuess = originalGame.makeGuess(guess)

        val expectedText = Text(
            listOf(
                Word("Paul", redacted = false),
                Space,
                Word("Atreides", redacted = true)
            )
        )

        val expectedGuesses = listOf(GuessWithMatches(Guess(guess.value.lowercase().trim()), 2))

        assertEquals(expected = expectedGuesses, actual = gameWithGuess.guesses)
        assertEquals(expected = expectedText, actual = gameWithGuess.text)
        assertEquals(expected = expectedText, actual = gameWithGuess.title)
        assertFalse(gameWithGuess.isCompleted())
    }

    @Test
    fun `when making a guess that is empty the same game should be returned without the guess`() {
        val text = TextParser.parse("Paul Atreides")
        val originalGame = Game(text, text)
        val guess = Guess("")

        val gameWithGuess = originalGame.makeGuess(guess)

        assertEquals(expected = emptyList(), actual = gameWithGuess.guesses)
    }

    @Test
    fun `when making an incorrect guess the guess count should be increased`() {
        val text = TextParser.parse("Paul Atreides")
        val originalGame = Game(text, text)
        val guess = Guess("Dune")

        val gameWithGuess = originalGame.makeGuess(guess)

        val expectedText = Text(
            listOf(
                Word("Paul", redacted = true),
                Space,
                Word("Atreides", redacted = true)
            )
        )

        val expectedGuesses = listOf(GuessWithMatches(Guess(guess.value.lowercase()), 0))

        assertEquals(expected = expectedGuesses, actual = gameWithGuess.guesses)
        assertEquals(expected = expectedText, actual = gameWithGuess.text)
        assertEquals(expected = expectedText, actual = gameWithGuess.title)
        assertFalse(gameWithGuess.isCompleted())
    }

    @Test
    fun `when making the same incorrect guess more than once the guess count should not be increased`() {
        val text = TextParser.parse("Paul Atreides")
        val originalGame = Game(text, text)
        val guess = Guess("Dune")

        val gameWithGuesses = originalGame
            .makeGuess(guess)
            .makeGuess(guess)
            .makeGuess(guess)

        val expectedText = Text(
            listOf(
                Word("Paul", redacted = true),
                Space,
                Word("Atreides", redacted = true)
            )
        )

        val expectedGuesses = listOf(GuessWithMatches(Guess(guess.value.lowercase()), 0))

        assertEquals(expected = expectedGuesses, actual = gameWithGuesses.guesses)
        assertEquals(expected = expectedText, actual = gameWithGuesses.text)
        assertEquals(expected = expectedText, actual = gameWithGuesses.title)
        assertFalse(gameWithGuesses.isCompleted())
    }

    @Test
    fun `when making the same guess twice the guess count should only increase once`() {
        val text = TextParser.parse("Paul Atreides")
        val originalGame = Game(text, text)
        val guess = Guess("Paul")

        val gameWithGuess = originalGame
            .makeGuess(guess)
            .makeGuess(guess)

        val expectedText = Text(
            listOf(
                Word("Paul", redacted = false),
                Space,
                Word("Atreides", redacted = true)
            )
        )

        val expectedGuesses = listOf(GuessWithMatches(Guess(guess.value.lowercase()), 2))

        assertEquals(expected = expectedGuesses, actual = gameWithGuess.guesses)
        assertEquals(expected = expectedText, actual = gameWithGuess.text)
        assertEquals(expected = expectedText, actual = gameWithGuess.title)
        assertFalse(gameWithGuess.isCompleted())
    }

    @Test
    fun `when correctly guessing all words in title the game state should be completed`() {
        val text = TextParser.parse("Paul Atreides")
        val originalGame = Game(text, text)
        val guess1 = Guess("Paul")
        val guess2 = Guess("Atreides")

        val gameWithGuess = originalGame
            .makeGuess(guess1)
            .makeGuess(guess2)

        val expectedText = Text(
            listOf(
                Word("Paul", redacted = false),
                Space,
                Word("Atreides", redacted = false)
            )
        )

        val expectedGuesses = listOf(
            GuessWithMatches(Guess(guess1.value.lowercase()), 2),
            GuessWithMatches(Guess(guess2.value.lowercase()), 2)
        )

        assertEquals(expected = expectedGuesses, actual = gameWithGuess.guesses)
        assertEquals(expected = expectedText, actual = gameWithGuess.text)
        assertEquals(expected = expectedText, actual = gameWithGuess.title)
        assertTrue(gameWithGuess.isCompleted())
    }

    @Test
    fun `when guessing a word that is only present in the title - game is updated correctly`() {
        val title = TextParser.parse("Dune Messiah")
        val text = TextParser.parse("Paul Atreides")
        val guess1 = Guess("Dune")

        val originalGame = Game(title, text)

        val gameAfterFirstGuess = originalGame.makeGuess(guess1)

        val expectedTitleAfterFirstGuess = Text(
            listOf(
                Word("Dune", redacted = false),
                Space,
                Word("Messiah", redacted = true)
            )
        )

        val expectedGuesses1 = listOf(GuessWithMatches(Guess(guess1.value.lowercase()), 1))

        assertEquals(expected = expectedGuesses1, actual = gameAfterFirstGuess.guesses)
        assertEquals(expected = text, actual = gameAfterFirstGuess.text)
        assertEquals(expected = expectedTitleAfterFirstGuess, actual = gameAfterFirstGuess.title)
        assertFalse(gameAfterFirstGuess.isCompleted())

        val guess2 = Guess("Messiah")
        val gameAfterSecondGuess = gameAfterFirstGuess.makeGuess(guess2)

        val expectedTitleAfterSecondGuess = Text(
            listOf(
                Word("Dune", redacted = false),
                Space,
                Word("Messiah", redacted = false)
            )
        )

        val expectedTextAfterSecondGuess = Text(
            listOf(
                Word("Paul", redacted = false),
                Space,
                Word("Atreides", redacted = false)
            )
        )

        val expectedGuesses2 = listOf(
            GuessWithMatches(Guess(guess1.value.lowercase()), 1),
            GuessWithMatches(Guess(guess2.value.lowercase()), 1)
        )

        assertEquals(expected = expectedGuesses2, actual = gameAfterSecondGuess.guesses)
        assertEquals(expected = expectedTextAfterSecondGuess, actual = gameAfterSecondGuess.text)
        assertEquals(expected = expectedTitleAfterSecondGuess, actual = gameAfterSecondGuess.title)
        assertTrue(gameAfterSecondGuess.isCompleted())
    }

    @Test
    fun `when guessing a word that is a present in both the title and the text - game is updated correctly`() {
        val title = TextParser.parse("Dune")
        val text = TextParser.parse("Arrakis is another name for Dune.")
        val guess1 = Guess("Arrakis")

        val originalGame = Game(title, text)

        val gameAfterFirstGuess = originalGame.makeGuess(guess1)

        val expectedTextAfterFirstGuess = Text(
            listOf(
                Word("Arrakis", redacted = false),
                Space,
                Word("is", redacted = true),
                Space,
                Word("another", redacted = true),
                Space,
                Word("name", redacted = true),
                Space,
                Word("for", redacted = true),
                Space,
                Word("Dune", redacted = true),
                Punctuation('.')
            )
        )

        val expectedGuesses1 = listOf(GuessWithMatches(Guess(guess1.value.lowercase()), 1))

        assertEquals(expected = expectedGuesses1, actual = gameAfterFirstGuess.guesses)
        assertEquals(expected = expectedTextAfterFirstGuess, actual = gameAfterFirstGuess.text)
        assertEquals(expected = title, actual = gameAfterFirstGuess.title)
        assertFalse(gameAfterFirstGuess.isCompleted())

        val guess2 = Guess("Dune")
        val gameAfterSecondGuess = gameAfterFirstGuess.makeGuess(guess2)

        val expectedTitleAfterSecondGuess = Text(
            listOf(
                Word("Dune", redacted = false)
            )
        )

        val expectedTextAfterSecondGuess = Text(
            listOf(
                Word("Arrakis", redacted = false),
                Space,
                Word("is", redacted = false),
                Space,
                Word("another", redacted = false),
                Space,
                Word("name", redacted = false),
                Space,
                Word("for", redacted = false),
                Space,
                Word("Dune", redacted = false),
                Punctuation('.')
            )
        )

        val expectedGuesses2 = listOf(
            GuessWithMatches(Guess(guess1.value.lowercase()), 1),
            GuessWithMatches(Guess(guess2.value.lowercase()), 2)
        )

        assertEquals(expected = expectedGuesses2, actual = gameAfterSecondGuess.guesses)
        assertEquals(expected = expectedTextAfterSecondGuess, actual = gameAfterSecondGuess.text)
        assertEquals(expected = expectedTitleAfterSecondGuess, actual = gameAfterSecondGuess.title)
        assertTrue(gameAfterSecondGuess.isCompleted())
    }

    @Test
    fun `the latest guess returned is the last guess`() {
        val text = TextParser.parse("Paul Atreides")
        val game = Game(text, text)

        assertNull(game.latestGuess)

        val firstGuess = Guess("atreides")
        val gameAfterFirstCorrectGuess = game.makeGuess(firstGuess)

        assertEquals(expected = firstGuess, actual = gameAfterFirstCorrectGuess.latestGuess)

        val secondGuess = Guess("Paul")
        val gameAfterSecondCorrectGuess = game.makeGuess(secondGuess)

        assertEquals(expected = Guess("paul"), actual = gameAfterSecondCorrectGuess.latestGuess)
    }

    @Test
    fun `the latest guessed word returned is the last guess even if not found`() {
        val text = TextParser.parse("Paul Atreides")
        val game = Game(text, text)

        assertNull(game.latestGuess)

        val guess = Guess("Dune")
        val gameAfterIncorrectGuess = game.makeGuess(guess)

        assertEquals(expected = Guess("dune"), actual = gameAfterIncorrectGuess.latestGuess)
    }
}