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
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import se.phan.redacted.android.R
import se.phan.redacted.android.ui.component.Keyboard
import se.phan.redacted.android.ui.component.keyboardState
import se.phan.redacted.android.ui.theme.ApplicationTheme

private val FontSize = 24.sp
private val PlaceholderColor = Color(0xAAFFFFFF)

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun GuessTextField(modifier: Modifier = Modifier, onGuess: (String) -> Unit) {

    var text by remember { mutableStateOf("") }

    val focusRequester = FocusRequester()

    val keyboardState by keyboardState()
    val keyboardController = LocalSoftwareKeyboardController.current

    val toggleKeyboardIcon = @Composable {
        IconButton(
            onClick = {
                if (keyboardState == Keyboard.Opened) {
                    keyboardController?.hide()
                } else {
                    keyboardController?.show()
                }
            },
        ) {
            Icon(
                imageVector = if (keyboardState == Keyboard.Opened) {
                    Icons.Default.KeyboardArrowDown
                } else {
                    Icons.Default.KeyboardArrowUp
                },
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
        modifier = modifier.focusRequester(focusRequester),
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
            onSearch = {
                onGuess(text)
                text = ""
            }
        ),
        singleLine = true,
        shape = RoundedCornerShape(0.dp),
        colors = guessTextFieldColors()
    )

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
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
            GuessTextField(onGuess = {})
        }
    }
}