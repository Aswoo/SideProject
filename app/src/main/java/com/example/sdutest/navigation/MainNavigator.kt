package com.example.sdutest.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.sdutest.feature.bookmarks.navigation.navigateToBookmarks
import com.example.sdutest.feature.main.navigation.navigateToMain
import com.example.sdutest.feature.setting.navigation.navigateToSetting
import com.example.sdutest.ui.MainTab

internal class MainNavigator(
    val navController: NavHostController,
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val startDestination = MainTab.HOME.route

    val currentTab: MainTab?
        @Composable get() = currentDestination
            ?.route
            ?.let(MainTab::find)

    fun navigate(tab: MainTab) {
        val navOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (tab) {
            MainTab.SETTING -> navController.navigateToSetting(navOptions)
            MainTab.HOME -> navController.navigateToMain(navOptions)
            MainTab.BOOKMARK -> navController.navigateToBookmarks(navOptions)
        }
    }

//    fun navigateContributor() {
//        navController.navigateContributor()
//    }
//
//    fun navigateSession() {
//        navController.navigateSession()
//    }
//
//    fun navigateSessionDetail(sessionId: String) {
//        navController.navigateSessionDetail(sessionId)
//    }

    fun popBackStack() {
        navController.popBackStack()
    }

//    fun popBackStackIfNotHome() {
//        if (!isSameCurrentDestination(HomeRoute.route)) {
//            popBackStack()
//        }
//    }

    private fun isSameCurrentDestination(route: String) =
        navController.currentDestination?.route == route

    @Composable
    fun shouldShowBottomBar(): Boolean {
        val currentRoute = currentDestination?.route ?: return false
        return currentRoute in MainTab
    }
}

@Composable
internal fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}
