package com.example.sdutest.feature.bookmarks.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.sdutest.feature.bookmarks.BookmarksRoute


fun NavController.navigateToBookmarks(navOptions: NavOptions? = null) {
    this.navigate(BookmarksRoute.route, navOptions)
}

fun NavGraphBuilder.bookmarksScreen(
    onTopicClick: (String) -> Unit,
    onShowSnackbar: suspend (String, String?) -> Boolean,
) {
    composable(route = BookmarksRoute.route) {
        BookmarksRoute(onTopicClick, onShowSnackbar)
    }
}

object BookmarksRoute {
    const val route = "bookmark"
}
