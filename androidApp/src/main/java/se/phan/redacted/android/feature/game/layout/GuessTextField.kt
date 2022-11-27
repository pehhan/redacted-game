package se.phan.redacted.android.feature.game.layout

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import se.phan.redacted.android.ui.theme.ApplicationTheme

@Composable
fun GuessTextField(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { newText ->
            text = newText
        },
        modifier = modifier,
        textStyle = TextStyle(fontSize = 24.sp),
        singleLine = true,
        shape = RoundedCornerShape(0.dp),
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colors.onPrimary,
            backgroundColor = MaterialTheme.colors.primary,
            cursorColor = MaterialTheme.colors.onPrimary
        )
    )
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun GuessTextFieldPreview() {
    ApplicationTheme {
        Box {
            GuessTextField()
        }
    }
}