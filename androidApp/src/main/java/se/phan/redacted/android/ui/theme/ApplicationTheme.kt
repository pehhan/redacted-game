package se.phan.redacted.android.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable

@Composable
fun ApplicationTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    MaterialTheme(
        colors = themeColors(darkTheme),
        typography = themeTypography(),
        shapes = themeShapes(),
        content = content
    )
}