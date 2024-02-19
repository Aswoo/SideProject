package com.example.sdutest.feature.main


import com.example.sdutest.core.model.PokeSession
import com.example.sdutest.core.model.Session
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

sealed interface SessionUiState {
    object Loading : SessionUiState
    data class Sessions(
        val sessions: PersistentList<PokeSession> = persistentListOf(),
    ) : SessionUiState
}
