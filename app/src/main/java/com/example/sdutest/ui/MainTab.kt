package com.example.sdutest.ui

import com.example.sdutest.R
import com.example.sdutest.feature.bookmarks.navigation.BookmarksRoute
import com.example.sdutest.feature.main.navigation.SessionRoute
import com.example.sdutest.feature.setting.navigation.SettingRoute



internal enum class MainTab(
    val iconResId: Int,
    internal val contentDescription: String,
    val route: String,
) {
    SETTING(
        iconResId = R.drawable.ic_setting,
        contentDescription = "설정",
        SettingRoute.route,
    ),
    HOME(
        iconResId = R.drawable.ic_home,
        contentDescription = "홈",
        SessionRoute.route,
    ),
    BOOKMARK(
        iconResId = R.drawable.ic_bookmark,
        contentDescription = "북마크",
        BookmarksRoute.route,
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
