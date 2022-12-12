package se.phan.redacted.text

import se.phan.redacted.guess.Guess

data class Word(val value: String, val redacted: Boolean) : TextPart() {

    constructor(value: String) : this(value, redacted = true)

    fun matches(guess: Guess): Boolean {
        return value.uppercase() == guess.value.trim().uppercase()
    }

    fun matches(word: Word): Boolean {
        return normalized().value.uppercase() == word.normalized().value.uppercase()
    }
}