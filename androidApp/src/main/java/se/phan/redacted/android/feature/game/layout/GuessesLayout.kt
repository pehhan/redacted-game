package se.phan.redacted.android.feature.game.layout

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import se.phan.redacted.Guess
import se.phan.redacted.GuessWithMatches
import se.phan.redacted.android.ui.theme.ApplicationTheme
import se.phan.redacted.android.ui.theme.Gray500
import se.phan.redacted.android.ui.theme.HorizontalPadding
import se.phan.redacted.android.ui.theme.Teal800

private val HorizontalPaddingBetweenChips = 8.dp
private val VerticalPaddingBetweenChips = 0.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GuessesLayout(modifier: Modifier = Modifier, guesses: List<GuessWithMatches>) {
    FlowRow(
        modifier = modifier
            .padding(horizontal = HorizontalPadding),
        mainAxisSpacing = HorizontalPaddingBetweenChips,
        crossAxisSpacing = VerticalPaddingBetweenChips
    ) {
        for (guessWithMatch in guesses.reversed()) {
            Chip(
                colors = ChipDefaults.chipColors(
                    backgroundColor = MaterialTheme.colors.background
                ),
                onClick = {
                    // TODO: Jump to first/next occurrence
                }
            ) {
                Text(
                    text = "${guessWithMatch.guess.value} (${guessWithMatch.matches})",
                    color = if (guessWithMatch.matches == 0) {
                        Gray500
                    } else {
                        Teal800
                    }
                )
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun GuessesLayoutPreview() {
    ApplicationTheme {
        GuessesLayout(
            guesses = listOf(GuessWithMatches(Guess("Frank"), 0), GuessWithMatches(Guess("Herbert"), 3)),
        )
    }
}