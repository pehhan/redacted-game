package se.phan.redacted.android.feature.game.layout

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import se.phan.redacted.android.R
import se.phan.redacted.android.ui.theme.ApplicationTheme

private val FontSize = 24.sp
private val PlaceholderColor = Color(0xAAFFFFFF)

@Composable
fun GuessTextField(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }

    val toggleKeyboardIcon = @Composable {
        IconButton(
            onClick = {
                // TODO: Minimize keyboard
            },
        ) {
            Icon(
                Icons.Default.KeyboardArrowDown,
                contentDescription = "",
                tint = MaterialTheme.colors.onPrimary
            )
        }
    }

    TextField(
        value = text,
        onValueChange = { newText ->
            text = newText
        },
        modifier = modifier,
        textStyle = TextStyle(fontSize = FontSize),
        placeholder = {
            Text(
                text = stringResource(R.string.game_guess_a_word),
                fontSize = FontSize
            )
        },
        trailingIcon = toggleKeyboardIcon,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onDone = {
                // TODO: Handle search
            }
        ),
        singleLine = true,
        shape = RoundedCornerShape(0.dp),
        colors = guessTextFieldColors()
    )
}

@Composable
private fun guessTextFieldColors() = TextFieldDefaults.textFieldColors(
    textColor = MaterialTheme.colors.onPrimary,
    backgroundColor = MaterialTheme.colors.primary,
    cursorColor = MaterialTheme.colors.onPrimary,
    placeholderColor = PlaceholderColor
)

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