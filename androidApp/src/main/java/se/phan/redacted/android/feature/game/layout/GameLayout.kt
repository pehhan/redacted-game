package se.phan.redacted.android.feature.game.layout

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import se.phan.redacted.android.ui.component.Background
import se.phan.redacted.android.ui.theme.ApplicationTheme

@Composable
fun GameLayout(title: String, text: String) {
    ApplicationTheme {
        Background {
            Box {
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    GameTitle(title)
                    GameText(text)
                }
                GuessTextField(
                    modifier = Modifier.align(Alignment.BottomStart).fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun GameTitle(title: String) {
    Text(text = title)
}

@Composable
private fun GameText(text: String) {
    Text(text = text)
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun GameLayoutPreview() {
    GameLayout("Dune", "Dune is a book by Frank Herbert.")
}