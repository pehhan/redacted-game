package se.phan.redacted

import se.phan.redacted.text.Text

sealed class GuessResult

data class WordRevealed(val text: Text, val matches: Int) : GuessResult()

object WordAlreadyRevealed: GuessResult()

object WordNotInText : GuessResult()