package se.phan.redacted.android.feature.game.layout

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import se.phan.redacted.GuessWithMatches
import se.phan.redacted.android.ui.theme.HorizontalPadding

private val HorizontalPaddingBetweenChips = 8.dp
private val VerticalPaddingBetweenChips = 0.dp

// TODO: Style
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
                onClick = {
                    // TODO: Jump to first/next occurrence
                }
            ) {
                Text("${guessWithMatch.guess.value} (${guessWithMatch.matches})")
            }
        }
    }
}