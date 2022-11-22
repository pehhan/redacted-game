package se.phan.redacted.android.feature.game

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import se.phan.redacted.Game
import se.phan.redacted.GameCreator
import se.phan.redacted.android.ui.component.Background
import se.phan.redacted.android.ui.theme.ApplicationTheme
import se.phan.redacted.difficulty.Difficulty
import se.phan.redacted.parser.TextParser
import se.phan.redacted.renderer.TextRenderer
import se.phan.redacted.renderer.TrueWordLengthPunctuationVisibleRenderer
import se.phan.redacted.util.Greeting

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
