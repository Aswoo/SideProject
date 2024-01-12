package com.example.sdutest.feature.main.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.sdutest.core.model.Session
import com.example.sdutest.feature.main.MainRouter
import com.example.sdutest.feature.main.SessionDetailScreen
import com.example.sdutest.feature.main.navigation.SessionRoute.detailRoute
import com.example.sdutest.feature.main.navigation.SessionRoute.route


fun NavController.navigateToMain(navOptions: NavOptions? = null) {
    this.navigate(route, navOptions)
}
fun NavController.navigateSessionDetail(sessionId: String) {
    navigate(SessionRoute.detailRoute(sessionId))
}

fun NavGraphBuilder.mainScreen(
    onBackClick: () -> Unit,
    onSessionClick: (Session) -> Unit,
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
    fun detailRoute(sessionId: String): String = "$route/$sessionId"
}
