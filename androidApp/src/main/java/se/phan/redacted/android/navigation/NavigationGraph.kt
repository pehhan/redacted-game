package se.phan.redacted.android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import se.phan.redacted.android.feature.game.navigation.GAME_SCREEN_ROUTE
import se.phan.redacted.android.feature.game.navigation.gameScreen
import se.phan.redacted.android.feature.gamecompleted.navigation.gameCompletedScreen
import se.phan.redacted.android.feature.gamecompleted.navigation.navigateToGameCompleted

@Composable
fun NavigationGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = GAME_SCREEN_ROUTE
    ) {
        gameScreen(
            onNavigateToGameCompletedScreen = {
                navController.navigateToGameCompleted()
            }
        )

        gameCompletedScreen()
    }
}