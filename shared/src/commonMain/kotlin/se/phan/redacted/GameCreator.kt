package se.phan.redacted

import se.phan.redacted.difficulty.Difficulty
import se.phan.redacted.text.Text

object GameCreator {

    fun createGame(title: Text, text: Text, difficulty: Difficulty): Game {
        val unredactedWords = difficulty.unredactedWordsProvider.getWords()
        return Game(title.unredactWords(unredactedWords), text.unredactWords(unredactedWords))
    }
}