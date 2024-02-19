package com.example.sdutest.feature.main.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.sdutest.core.model.PokeSession
import com.example.sdutest.feature.main.MainRouter
import com.example.sdutest.feature.main.SessionDetailScreen
import com.example.sdutest.feature.main.navigation.SessionRoute.route


fun NavController.navigateToMain(navOptions: NavOptions? = null) {
    this.navigate(route, navOptions)
}
fun NavController.navigateSessionDetail(enName: String) {
    navigate(SessionRoute.detailRoute(enName))
}

fun NavGraphBuilder.mainScreen(
    onBackClick: () -> Unit,
    onSessionClick: (PokeSession) -> Unit,
    onShowSnackbar: suspend (String, String?) -> Boolean,
) {
    composable(route = SessionRoute.route) {
        MainRouter(
            onBackClick = onBackClick,
            onSessionClick = onSessionClick,
            onShowSnackbar = onShowSnackbar
        )
    }

    composable(
        route = SessionRoute.detailRoute("{id}"),
        arguments = listOf(
            navArgument("id") {
                type = NavType.StringType
            }
        )
    ) { navBackStackEntry ->
        val sessionId = navBackStackEntry.arguments?.getString("id") ?: ""
        SessionDetailScreen(
            sessionId = sessionId,
            onBackClick = onBackClick
        )
    }
}
object SessionRoute {
    const val route: String = "main_route"
    fun detailRoute(enName: String): String = "$route/$enName"
}
