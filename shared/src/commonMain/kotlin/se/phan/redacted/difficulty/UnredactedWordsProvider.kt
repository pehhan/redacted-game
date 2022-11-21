package se.phan.redacted.difficulty

import se.phan.redacted.text.Word

interface UnredactedWordsProvider {
    fun getWords(): List<Word>
}