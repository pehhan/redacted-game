package se.phan.redacted.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import se.phan.redacted.android.feature.game.GameViewModel
import se.phan.redacted.android.feature.game.createDummyGame
import se.phan.redacted.android.feature.game.layout.GameLayout
import se.phan.redacted.renderer.TextRenderer
import se.phan.redacted.renderer.TrueWordLengthPunctuationVisibleRenderer

class RedactedActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val game = createDummyGame()
        val renderer: TextRenderer = TrueWordLengthPunctuationVisibleRenderer()
        val gameViewModel = GameViewModel(game)

        setContent {
            GameLayout(gameViewModel, renderer, gameViewModel::onGuess, gameViewModel::onGuessClick)
        }
    }
}
