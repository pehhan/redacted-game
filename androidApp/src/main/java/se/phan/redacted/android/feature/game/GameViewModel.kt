package se.phan.redacted.android.feature.game

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import se.phan.redacted.Game
import se.phan.redacted.Guess
import se.phan.redacted.GuessWithMatches
import se.phan.redacted.text.Text

class GameViewModel(private var game: Game) : ViewModel() {

    private val _title = MutableStateFlow(title())
    private val _text = MutableStateFlow(text())
    private val _guesses = MutableStateFlow(guesses())
    private val _latestGuess = MutableStateFlow(latestGuess())

    val title: StateFlow<Text> = _title.asStateFlow()
    val text: StateFlow<Text> = _text.asStateFlow()
    val guesses: StateFlow<List<GuessWithMatches>> = _guesses.asStateFlow()
    val latestGuess: StateFlow<Guess?> = _latestGuess.asStateFlow()

    fun onGuess(guess: String) {
        game = game.makeGuess(Guess(guess))

        _title.value = title()
        _text.value = text()
        _guesses.value = guesses()
        _latestGuess.value = latestGuess()
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

    private fun latestGuess(): Guess? {
        return game.latestGuess
    }
}