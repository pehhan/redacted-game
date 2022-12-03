package se.phan.redacted.android.feature.game

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import se.phan.redacted.Game
import se.phan.redacted.Guess

class GameViewModel(game: Game) : ViewModel() {

    private val _state = MutableStateFlow(game)

    val state: StateFlow<Game> = _state.asStateFlow()

    fun onGuess(guess: String) {
        val newGame = state.value.makeGuess(Guess(guess))
        _state.value = newGame
    }
}