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

    private val _title = MutableStateFlow(renderer.render(game.title))
    private val _text = MutableStateFlow(renderer.render(game.text))
    private val _guesses = MutableStateFlow(game.guesses)

    val title: StateFlow<String> = _title.asStateFlow()
    val text: StateFlow<String> = _text.asStateFlow()
    val guesses: StateFlow<List<GuessWithMatches>> = _guesses.asStateFlow()

    fun onGuess(guess: String) {
        game = game.makeGuess(Guess(guess))

        _title.value = renderer.render(game.title)
        _text.value = renderer.render(game.text)
        _guesses.value = game.guesses
    }
}