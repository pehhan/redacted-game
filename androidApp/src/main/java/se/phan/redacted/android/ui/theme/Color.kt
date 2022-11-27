package se.phan.redacted.android.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors

fun themeColors(darkTheme: Boolean): Colors {
    return if (darkTheme) {
        darkColors(
            primary = Teal500,
            primaryVariant = Teal800,
            secondary = Teal500,
            secondaryVariant = Teal800,
            background = Black,
            surface =  Black,
            error = Red900,
            onPrimary = White,
            onSecondary = White,
            onBackground = White,
            onSurface = White,
            onError = White
        )
    } else {
        lightColors(
            primary = Teal500,
            primaryVariant = Teal800,
            secondary = Teal500,
            secondaryVariant = Teal800,
            background = White,
            surface = White,
            error = Red900,
            onPrimary = White,
            onSecondary = White,
            onBackground = Black,
            onSurface = Black,
            onError = White
        )
    }
}