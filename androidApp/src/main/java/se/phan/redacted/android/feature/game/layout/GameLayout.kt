package se.phan.redacted.android.feature.game.layout

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import se.phan.redacted.android.ui.component.Background
import se.phan.redacted.android.ui.theme.ApplicationTheme
import se.phan.redacted.android.ui.theme.HorizontalPadding
import se.phan.redacted.android.ui.theme.VerticalPadding

private val BottomTextPadding = 80.dp

@Composable
fun GameLayout(title: String, text: String) {
    ApplicationTheme {
        Background {
            Box {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(
                            start = HorizontalPadding,
                            top = VerticalPadding,
                            end = HorizontalPadding,
                            bottom = BottomTextPadding
                        ),
                    verticalArrangement = Arrangement.spacedBy(VerticalPadding)
                ) {
                    GameTitle(title)
                    GameText(text)
                }
                GuessTextField(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun GameTitle(title: String) {
    Text(
        text = title,
        fontSize = 28.sp
    )
}

@Composable
private fun GameText(text: String) {
    Text(
        text = text,
        fontSize = 18.sp,
        lineHeight = 28.sp
    )
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun GameLayoutPreview() {
    GameLayout("Dune", "Dune is a book by Frank Herbert.")
}