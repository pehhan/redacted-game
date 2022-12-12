package se.phan.redacted.text

import se.phan.redacted.guess.Guess
import se.phan.redacted.guess.GuessResult
import se.phan.redacted.guess.WordAlreadyRevealed
import se.phan.redacted.guess.WordNotInText
import se.phan.redacted.guess.WordRevealed

data class Text(val parts: List<TextPart>) {

    fun makeGuess(guess: Guess): GuessResult {
        return when (numberOfMatches(guess)) {
            0 -> WordNotInText
            else -> makeGuessForWordInText(guess)
        }
    }

    private fun makeGuessForWordInText(guess: Guess): GuessResult {
        return if (isGuessRedacted(guess)) {
            WordRevealed(revealText(guess), numberOfMatches(guess))
        } else {
            WordAlreadyRevealed
        }
    }

    fun areAllWordsRevealed(): Boolean {
        return parts
            .filterIsInstance<Word>()
            .all { !it.redacted }
    }

    fun revealAll(): Text {
        return revealText { true }
    }

    fun revealWords(words: List<Word>): Text {
        return revealText { word ->
            words.any { it.matches(word) }
        }
    }

    private fun revealText(guess: Guess): Text {
        return revealText { word ->
            word.matches(guess)
        }
    }

    private fun revealText(shouldRevealWord: (Word) -> Boolean): Text {
        val parts = parts.map { part ->
            if (part is Word && shouldRevealWord(part)) {
                part.copy(redacted = false)
            } else {
                part
            }
        }

        return Text(parts)
    }

    private fun numberOfMatches(guess: Guess): Int {
        return parts
            .filterIsInstance<Word>()
            .count { it.matches(guess) }
    }

    private fun isGuessRedacted(guess: Guess): Boolean {
        return parts
            .filterIsInstance<Word>()
            .filter { it.matches(guess) }
            .all { it.redacted }
    }
}
