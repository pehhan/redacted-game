package se.phan.redacted.android.feature.game.layout

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import se.phan.redacted.Guess
import se.phan.redacted.android.feature.game.createDummyGame
import se.phan.redacted.android.ui.component.Background
import se.phan.redacted.android.ui.theme.ApplicationTheme
import se.phan.redacted.layout.TextLayoutRepresentation
import se.phan.redacted.layout.toLayoutRepresentation
import se.phan.redacted.renderer.TextRenderer
import se.phan.redacted.renderer.TrueWordLengthPunctuationVisibleRenderer
import se.phan.redacted.text.Text
import se.phan.redacted.text.Word

private val VerticalPaddingBetweenParagraphs = 8.dp

@Composable
fun TextLayout(
    text: Text,
    latestGuess: Guess?,
    renderer: TextRenderer,
    fontSize: TextUnit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        for (representation in text.parts.toLayoutRepresentation()) {
            when (representation) {
                is TextLayoutRepresentation.Paragraph -> {
                    ParagraphLayout(
                        representation.parts,
                        latestGuess,
                        renderer,
                        fontSize
                    )
                }
                is TextLayoutRepresentation.VerticalSpace -> {
                    Spacer(modifier = Modifier.height(VerticalPaddingBetweenParagraphs))
                }
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TextLayoutPreview() {
    val renderer = TrueWordLengthPunctuationVisibleRenderer()

    ApplicationTheme {
        Background {
            TextLayout(
                text = createDummyGame().text,
                latestGuess = Guess("and"),
                renderer = renderer,
                fontSize = 18.sp
            )
        }
    }
}