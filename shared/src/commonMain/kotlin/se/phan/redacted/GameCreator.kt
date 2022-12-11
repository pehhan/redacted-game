package se.phan.redacted

import se.phan.redacted.difficulty.Difficulty
import se.phan.redacted.text.Text

object GameCreator {

    fun createGame(title: Text, text: Text, difficulty: Difficulty): Game {
        val revealedWords = difficulty.revealedWordsProvider.getWords()
        return Game(title.revealWords(revealedWords), text.revealWords(revealedWords))
    }
}