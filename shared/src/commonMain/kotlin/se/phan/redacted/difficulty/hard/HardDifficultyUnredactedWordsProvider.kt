package se.phan.redacted.difficulty.hard

import se.phan.redacted.difficulty.UnredactedWordsProvider
import se.phan.redacted.text.Word

class HardDifficultyUnredactedWordsProvider : UnredactedWordsProvider {

    override fun getWords(): List<Word> {
        return emptyList()
    }
}
