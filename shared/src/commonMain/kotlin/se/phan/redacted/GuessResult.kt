package se.phan.redacted

import se.phan.redacted.text.Text

sealed class GuessResult

data class WordUnredacted(val text: Text, val matches: Int) : GuessResult()

object WordAlreadyUnredacted: GuessResult()

object WordNotInText : GuessResult()