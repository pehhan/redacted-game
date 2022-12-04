package se.phan.redacted

import se.phan.redacted.text.Text

data class Game(val title: Text, val text: Text, val guesses: List<GuessWithMatches>) {

    constructor(title: Text, text: Text) : this(title, text, emptyList())

    fun isCompleted(): Boolean {
        return title.areAllWordsUnredacted()
    }

    fun makeGuess(guess: Guess): Game {
        if (guess.value.isBlank()) return this

        return when (val titleResult = title.makeGuess(guess)) {
            is WordUnredacted -> {
                if (titleResult.text.areAllWordsUnredacted()) {
                    val textMatches = matchesInTextForGuess(guess)
                    Game(titleResult.text, text.unredactAll(), guessesWithNewGuess(guess, textMatches + titleResult.matches))
                } else {
                    makeGuessForText(guess, titleResult.text, titleResult.matches)
                }
            }
            is WordAlreadyUnredacted, is WordNotInText -> {
                makeGuessForText(guess, title, 0)
            }
        }
    }

    private fun makeGuessForText(guess: Guess, title: Text, titleMatches: Int): Game {
        return when (val result = text.makeGuess(guess)) {
            is WordUnredacted -> Game(title, result.text, guessesWithNewGuess(guess, result.matches + titleMatches))
            is WordAlreadyUnredacted -> this
            is WordNotInText -> Game(title, text, guessesWithNewGuess(guess, titleMatches))
        }
    }

    private fun guessesWithNewGuess(guess: Guess, matches: Int): List<GuessWithMatches> {
        val guessWithMatches = GuessWithMatches(Guess(guess.value.lowercase().trim()), matches)

        return if (alreadyGuessed(guessWithMatches)) {
            guesses
        } else {
            guesses + guessWithMatches
        }
    }

    private fun alreadyGuessed(guessWithMatches: GuessWithMatches): Boolean {
        return guessWithMatches in guesses
    }

    private fun matchesInTextForGuess(guess: Guess): Int {
        return when (val result = text.makeGuess(guess)) {
            is WordUnredacted -> result.matches
            is WordAlreadyUnredacted, is WordNotInText -> 0
        }
    }
}
