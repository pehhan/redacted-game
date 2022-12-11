package se.phan.redacted.difficulty

import se.phan.redacted.difficulty.hard.HardDifficultyRevealedWordsProvider
import se.phan.redacted.difficulty.normal.NormalDifficultyRevealedWordsProvider

enum class Difficulty(val revealedWordsProvider: RevealedWordsProvider) {
    Normal(NormalDifficultyRevealedWordsProvider()),
    Hard(HardDifficultyRevealedWordsProvider())
}