package com.example.sdutest.ui

import com.example.sdutest.R
import com.example.sdutest.feature.main.navigation.mainRoute
import com.example.sdutest.feature.bookmarks.navigation.bookmarksRoute


internal enum class MainTab(
    val iconResId: Int,
    internal val contentDescription: String,
    val route: String,
) {
    SETTING(
        iconResId = R.drawable.ic_setting,
        contentDescription = "설정",
        mainRoute,
    ),
    HOME(
        iconResId = R.drawable.ic_home,
        contentDescription = "홈",
        mainRoute,
    ),
    BOOKMARK(
        iconResId = R.drawable.ic_bookmark,
        contentDescription = "북마크",
        bookmarksRoute,
    );

    companion object {
        operator fun contains(route: String): Boolean {
            return values().map { it.route }.contains(route)
        }

        fun find(route: String): MainTab? {
            return values().find { it.route == route }
        }
    }
}
