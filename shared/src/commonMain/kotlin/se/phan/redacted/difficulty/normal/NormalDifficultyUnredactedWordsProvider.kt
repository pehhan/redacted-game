package se.phan.redacted.difficulty.normal

import se.phan.redacted.difficulty.UnredactedWordsProvider
import se.phan.redacted.text.Word

class NormalDifficultyUnredactedWordsProvider : UnredactedWordsProvider {

    override fun getWords(): List<Word> {
        return listOf(
            "a",
            "an",
            "and",
            "as",
            "at",
            "but",
            "by",
            "for",
            "from",
            "if",
            "in",
            "is",
            "it",
            "of",
            "on",
            "or",
            "out",
            "the",
            "to",
            "with",
            "up"
        ).map { Word(it) }
    }
}