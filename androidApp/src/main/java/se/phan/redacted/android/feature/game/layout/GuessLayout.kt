package se.phan.redacted.android.feature.game.layout

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import se.phan.redacted.Guess
import se.phan.redacted.android.ui.theme.ApplicationTheme

@Composable
fun GuessLayout(
    modifier: Modifier = Modifier,
    guesses: List<Guess>,
    onGuess: (String) -> Unit
) {
    Column(modifier = modifier.background(MaterialTheme.colors.primary)) {
        GuessesLayout(guesses = guesses)
        Divider(thickness = 2.dp, color = MaterialTheme.colors.primaryVariant)
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
            guesses = listOf(Guess("Frank"), Guess("Herbert")),
            onGuess = {}
        )
    }
}