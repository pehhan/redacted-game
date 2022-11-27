package se.phan.redacted.android.feature.game

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import se.phan.redacted.android.feature.game.layout.GameLayout
import se.phan.redacted.renderer.TextRenderer
import se.phan.redacted.renderer.TrueWordLengthPunctuationVisibleRenderer

class GameActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val game = createDummyGame()
        val renderer: TextRenderer = TrueWordLengthPunctuationVisibleRenderer()

        setContent {
            GameLayout(renderer.render(game.title), renderer.render(game.text))
        }
    }
}
