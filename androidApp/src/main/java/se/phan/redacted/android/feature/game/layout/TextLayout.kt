package se.phan.redacted.android.feature.game.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import se.phan.redacted.android.ui.theme.HorizontalPadding
import se.phan.redacted.layout.TextLayoutRepresentation
import se.phan.redacted.layout.toLayoutRepresentation
import se.phan.redacted.renderer.TextRenderer
import se.phan.redacted.text.Newline
import se.phan.redacted.text.Punctuation
import se.phan.redacted.text.Space
import se.phan.redacted.text.Text
import se.phan.redacted.text.TextPart
import se.phan.redacted.text.Word

private val VerticalPaddingBetweenParagraphs = 8.dp

@Composable
fun TextLayout(
    text: Text,
    renderer: TextRenderer,
    fontSize: TextUnit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        for (representation in text.parts.toLayoutRepresentation()) {
            when (representation) {
                is TextLayoutRepresentation.Paragraph -> {
                    Paragraph(
                        representation.parts,
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

@Composable
fun Paragraph(
    textParts: List<TextPart>,
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
                is Word, is Punctuation -> {
                    Text(
                        text = renderer.render(textPart),
                        modifier = modifier,
                        fontSize = fontSize,
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