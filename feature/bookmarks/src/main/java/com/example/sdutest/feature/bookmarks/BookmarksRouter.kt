package com.example.sdutest.feature.bookmarks

import androidx.compose.runtime.Composable

@Composable
internal fun BookmarksRoute(
    onTopicClick: (String) -> Unit,
    onShowSnackbar: suspend (String, String?) -> Boolean,
) {
    BookmarkRoute(onShowSnackbar)
}