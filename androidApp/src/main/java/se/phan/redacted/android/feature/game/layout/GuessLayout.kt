package se.phan.redacted.android.feature.game.layout

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import se.phan.redacted.guess.Guess
import se.phan.redacted.guess.GuessWithMatches
import se.phan.redacted.android.ui.component.GradientBox
import se.phan.redacted.android.ui.theme.ApplicationTheme

private val GuessesLayoutMaxHeight = 75.dp
private val GuessesLayoutRoundedCorner = 20.dp
private val GuessesLayoutFadingGradientHeight = 10.dp
private val DividerThickness = 2.dp

@Composable
fun GuessLayout(
    modifier: Modifier = Modifier,
    guesses: List<GuessWithMatches>,
    onGuess: (String) -> Unit,
    onGuessClick: (Guess) -> Unit
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(topStart = GuessesLayoutRoundedCorner, topEnd = GuessesLayoutRoundedCorner))
            .background(MaterialTheme.colors.primary)
    ) {

        val stateVertical = rememberScrollState(0)

        Box {
            GuessesLayout(
                modifier = Modifier
                    .heightIn(max = GuessesLayoutMaxHeight)
                    .verticalScroll(stateVertical),
                guesses = guesses,
                onGuessClick = onGuessClick
            )

            if (guesses.isNotEmpty()) {
                GradientBox(
                    height = GuessesLayoutFadingGradientHeight,
                    fromColor = MaterialTheme.colors.primary,
                    toColor = Color.Transparent,
                    modifier = Modifier.align(Alignment.TopCenter),
                )

                GradientBox(
                    height = GuessesLayoutFadingGradientHeight,
                    fromColor = Color.Transparent,
                    toColor = MaterialTheme.colors.primary,
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }
        }
        Divider(
            thickness = DividerThickness,
            color = MaterialTheme.colors.primaryVariant
        )
        GuessTextField(
            modifier = Modifier.fillMaxWidth(),
            onGuess = onGuess
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun GuessLayoutPreview() {
    ApplicationTheme {
        GuessLayout(
            guesses = listOf(GuessWithMatches(Guess("Frank"), 0), GuessWithMatches(Guess("Herbert"), 3)),
            onGuess = {},
            onGuessClick = {}
        )
    }
}