package se.phan.redacted.android.feature.game.layout

import android.content.res.Configuration
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.flowlayout.FlowRow
import se.phan.redacted.android.feature.game.createDummyGame
import se.phan.redacted.android.ui.component.Background
import se.phan.redacted.android.ui.theme.ApplicationTheme
import se.phan.redacted.android.ui.theme.HorizontalPadding
import se.phan.redacted.renderer.TextRenderer
import se.phan.redacted.renderer.TrueWordLengthPunctuationVisibleRenderer
import se.phan.redacted.text.Newline
import se.phan.redacted.text.Punctuation
import se.phan.redacted.text.Space
import se.phan.redacted.text.TextPart
import se.phan.redacted.text.Word

@Composable
fun ParagraphLayout(
    textParts: List<TextPart>,
    latestGuessedWord: Word?,
    renderer: TextRenderer,
    fontSize: TextUnit,
    modifier: Modifier = Modifier
) {
    FlowRow(
        modifier = modifier
            .padding(horizontal = HorizontalPadding),
        mainAxisSpacing = 0.dp,
        crossAxisSpacing = 0.dp
    ) {
        for (textPart in textParts) {
            when (textPart) {
                is Word -> {
                    WordLayout(
                        word = textPart,
                        latestGuessedWord = latestGuessedWord,
                        renderer = renderer,
                        fontSize = fontSize,
                    )
                }
                is Punctuation -> {
                    Text(
                        text = renderer.render(textPart),
                        fontSize = fontSize
                    )
                }
                is Space -> {
                    Spacer(modifier = Modifier.width(4.dp))
                }
                is Newline -> {
                    // Do nothing
                }
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ParagraphLayoutPreview() {
    val renderer = TrueWordLengthPunctuationVisibleRenderer()

    ApplicationTheme {
        Background {
            ParagraphLayout(
                textParts = createDummyGame().text.parts,
                latestGuessedWord = Word("and", redacted = false),
                renderer = renderer,
                fontSize = 18.sp
            )
        }
    }
}