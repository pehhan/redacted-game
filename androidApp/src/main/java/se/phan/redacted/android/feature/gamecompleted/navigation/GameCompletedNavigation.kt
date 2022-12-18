package se.phan.redacted.android.feature.gamecompleted.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import se.phan.redacted.android.feature.gamecompleted.layout.GameCompletedScreen

private const val GAME_COMPLETED_ROUTE = "game_completed_screen"

fun NavGraphBuilder.gameCompletedScreen() {
    composable(route = GAME_COMPLETED_ROUTE) {
        GameCompletedScreen()
    }
}

fun NavController.navigateToGameCompleted() {
    // TODO: Should be able to pass in which game
    navigate(GAME_COMPLETED_ROUTE)
}