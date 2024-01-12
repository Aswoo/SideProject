package com.example.sdutest.feature.bookmarks

sealed interface BookmarkUiState {
    object Loading : BookmarkUiState
    data class Success(val isEditButtonSelected: Boolean = false, val bookmarks: List<BookmarkItemUiState> = listOf()) :
        BookmarkUiState
}
