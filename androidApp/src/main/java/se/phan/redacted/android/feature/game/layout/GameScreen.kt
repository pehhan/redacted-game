package se.phan.redacted.android.feature.game.layout

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import se.phan.redacted.android.feature.game.GameViewModel
import se.phan.redacted.android.feature.game.createDummyGame
import se.phan.redacted.android.ui.theme.HorizontalPadding
import se.phan.redacted.android.ui.theme.VerticalPadding
import se.phan.redacted.renderer.TextRenderer
import se.phan.redacted.renderer.TrueWordLengthPunctuationVisibleRenderer

@Composable
fun GameScreen(
    gameViewModel: GameViewModel,
    renderer: TextRenderer,
    onNavigateToGameCompletedScreen: () -> Unit
) {
    val title by gameViewModel.title.collectAsState()
    val text by gameViewModel.text.collectAsState()
    val guesses by gameViewModel.guesses.collectAsState()
    val latestGuess by gameViewModel.latestGuess.collectAsState()
    val completed by gameViewModel.completed.collectAsState()

    val localDensity = LocalDensity.current

    var guessLayoutHeight by remember {
        mutableStateOf(0.dp)
    }

    // TODO: Is this how it should be done? Feels a bit strange
    if (completed) {
        onNavigateToGameCompletedScreen()
    }

    Box {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(
                    start = HorizontalPadding,
                    top = VerticalPadding,
                    end = HorizontalPadding,
                    bottom = guessLayoutHeight + VerticalPadding
                ),
            verticalArrangement = Arrangement.spacedBy(VerticalPadding)
        ) {
            TextLayout(
                title,
                latestGuess,
                renderer,
                fontSize = 28.sp
            )
            TextLayout(
                text,
                latestGuess,
                renderer,
                fontSize = 18.sp
            )
        }
        GuessLayout(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    guessLayoutHeight = with(localDensity) { coordinates.size.height.toDp() }
                },
            guesses = guesses,
            onGuess = { guess ->
                gameViewModel.onGuess(guess)
            },
            onGuessClick = { guess ->
                gameViewModel.onGuessClick(guess)
            }
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun GameScreenPreview() {
    val renderer = TrueWordLengthPunctuationVisibleRenderer()
    val gameViewModel = GameViewModel(createDummyGame())

    GameScreen(gameViewModel, renderer) {}
}