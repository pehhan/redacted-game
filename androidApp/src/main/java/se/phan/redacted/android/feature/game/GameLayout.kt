package se.phan.redacted.android.feature.game

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import se.phan.redacted.android.ui.component.Background
import se.phan.redacted.android.ui.theme.ApplicationTheme

@Composable
fun GameLayout(title: String, text: String) {
    ApplicationTheme {
        Background {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                GameTitle(title)
                GameText(text)
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