package se.phan.redacted.difficulty

import se.phan.redacted.difficulty.hard.HardDifficultyUnredactedWordsProvider
import se.phan.redacted.difficulty.normal.NormalDifficultyUnredactedWordsProvider

enum class Difficulty(val unredactedWordsProvider: UnredactedWordsProvider) {
    Normal(NormalDifficultyUnredactedWordsProvider()),
    Hard(HardDifficultyUnredactedWordsProvider())
}