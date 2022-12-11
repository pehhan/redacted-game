package se.phan.redacted.android.feature.game

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import se.phan.redacted.Game
import se.phan.redacted.Guess
import se.phan.redacted.GuessWithMatches
import se.phan.redacted.text.Text
import se.phan.redacted.text.Word

class GameViewModel(private var game: Game) : ViewModel() {

    private val _title = MutableStateFlow(title())
    private val _text = MutableStateFlow(text())
    private val _guesses = MutableStateFlow(guesses())
    private val _latestGuessedWord = MutableStateFlow(latestGuessedWord())

    val title: StateFlow<Text> = _title.asStateFlow()
    val text: StateFlow<Text> = _text.asStateFlow()
    val guesses: StateFlow<List<GuessWithMatches>> = _guesses.asStateFlow()
    val latestGuessedWord: StateFlow<Word?> = _latestGuessedWord.asStateFlow()

    fun onGuess(guess: String) {
        game = game.makeGuess(Guess(guess))

        _title.value = title()
        _text.value = text()
        _guesses.value = guesses()
        _latestGuessedWord.value = latestGuessedWord()
    }

    private fun title(): Text {
        return game.title
    }

    private fun text(): Text {
        return game.text
    }

    private fun guesses(): List<GuessWithMatches> {
        return game.guesses
    }

    private fun latestGuessedWord(): Word? {
        return game.latestGuessedWord
    }
}