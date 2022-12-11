package se.phan.redacted.android.feature.game.layout

import android.content.res.Configuration
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import se.phan.redacted.android.ui.component.Background
import se.phan.redacted.android.ui.theme.ApplicationTheme
import se.phan.redacted.renderer.TextRenderer
import se.phan.redacted.renderer.TrueWordLengthPunctuationVisibleRenderer
import se.phan.redacted.text.Word

@Composable
fun WordLayout(
    word: Word,
    latestGuessedWord: Word?,
    renderer: TextRenderer,
    modifier: Modifier = Modifier,
    fontSize: TextUnit
) {
    val color = if (latestGuessedWord != null && word.matches(latestGuessedWord)) {
        MaterialTheme.colors.primary
    } else {
        MaterialTheme.colors.onBackground
    }

    Text(
        text = renderer.render(word),
        modifier = modifier,
        fontSize = fontSize,
        color = color
    )
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun WordLayoutPreview() {
    val renderer = TrueWordLengthPunctuationVisibleRenderer()

    ApplicationTheme {
        Background {
            WordLayout(
                word = Word("Dune", redacted = false),
                latestGuessedWord = null,
                renderer = renderer,
                fontSize = 18.sp
            )
        }
    }
}