package com.example.sdutest.feature.main

sealed interface SessionDetailEffect {
    object Idle : SessionDetailEffect
    data class ShowToastForBookmarkState(val bookmarked: Boolean) : SessionDetailEffect
}
