package se.phan.redacted.android.feature.game

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import se.phan.redacted.Game
import se.phan.redacted.Guess
import se.phan.redacted.GuessWithMatches
import se.phan.redacted.renderer.TextRenderer

class GameViewModel(private var game: Game, private val renderer: TextRenderer) : ViewModel() {

    private val _title = MutableStateFlow(title())
    private val _text = MutableStateFlow(text())
    private val _guesses = MutableStateFlow(guesses())

    val title: StateFlow<String> = _title.asStateFlow()
    val text: StateFlow<String> = _text.asStateFlow()
    val guesses: StateFlow<List<GuessWithMatches>> = _guesses.asStateFlow()

    fun onGuess(guess: String) {
        game = game.makeGuess(Guess(guess))

        _title.value = title()
        _text.value = text()
        _guesses.value = guesses()
    }

    private fun title(): String {
        return renderer.render(game.title)
    }

    private fun text(): String {
        return renderer.render(game.text)
    }

    private fun guesses(): List<GuessWithMatches> {
        return game.guesses
    }
}