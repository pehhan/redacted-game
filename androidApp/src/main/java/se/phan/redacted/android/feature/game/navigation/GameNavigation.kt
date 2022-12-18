package se.phan.redacted.android.feature.game.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import se.phan.redacted.android.feature.game.GameViewModel
import se.phan.redacted.android.feature.game.createDummyGame
import se.phan.redacted.android.feature.game.layout.GameScreen
import se.phan.redacted.renderer.TextRenderer
import se.phan.redacted.renderer.TrueWordLengthPunctuationVisibleRenderer

const val GAME_SCREEN_ROUTE = "game_screen"

fun NavGraphBuilder.gameScreen(
    onNavigateToGameCompletedScreen: () -> Unit
) {
    composable(route = GAME_SCREEN_ROUTE) {
        // TODO: Should not be hard coded here
        val game = createDummyGame()
        val renderer: TextRenderer = TrueWordLengthPunctuationVisibleRenderer()
        val gameViewModel = GameViewModel(game)

        GameScreen(gameViewModel, renderer, onNavigateToGameCompletedScreen)
    }
}