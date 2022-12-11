package se.phan.redacted.difficulty

import se.phan.redacted.text.Word

interface RevealedWordsProvider {
    fun getWords(): List<Word>
}