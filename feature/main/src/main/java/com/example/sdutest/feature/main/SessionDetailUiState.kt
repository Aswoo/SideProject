package com.example.sdutest.feature.main

import com.example.sdutest.core.model.Session


sealed interface SessionDetailUiState {

    object Loading : SessionDetailUiState

    data class Success(val session: Session, val bookmarked: Boolean = false) : SessionDetailUiState
}
