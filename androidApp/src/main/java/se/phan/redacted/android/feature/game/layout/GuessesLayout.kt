package se.phan.redacted.android.feature.game.layout

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import se.phan.redacted.Guess

// TODO: Style
// TODO: Show # of matches
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GuessesLayout(modifier: Modifier = Modifier, guesses: List<Guess>) {
    FlowRow(modifier = modifier.padding(horizontal = 4.dp)) {
        for (guess in guesses.reversed()) {
            Chip(
                modifier = Modifier.padding(horizontal = 4.dp),
                onClick = {
                    // TODO: Jump to first/next occurrence
                }
            ) {
                Text("${guess.value} (x)")
            }
        }
    }
}