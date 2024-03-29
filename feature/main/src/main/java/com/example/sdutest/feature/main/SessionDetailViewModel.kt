package com.example.sdutest.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sdutest.core.domain.usecase.BookmarkSessionUseCase
import com.example.sdutest.core.domain.usecase.GetBookmarkedSessionIdsUseCase
import com.example.sdutest.core.domain.usecase.GetPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SessionDetailViewModel @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase,
    private val getBookmarkedSessionIdsUseCase: GetBookmarkedSessionIdsUseCase,
    private val bookmarkSessionUseCase: BookmarkSessionUseCase,
) : ViewModel() {

    private val _sessionUiState =
        MutableStateFlow<SessionDetailUiState>(SessionDetailUiState.Loading)
    val sessionUiState: StateFlow<SessionDetailUiState> = _sessionUiState

    private val _sessionUiEffect = MutableStateFlow<SessionDetailEffect>(SessionDetailEffect.Idle)
    val sessionUiEffect: StateFlow<SessionDetailEffect> = _sessionUiEffect

    init {
        viewModelScope.launch {
            combine(
                sessionUiState,
                getBookmarkedSessionIdsUseCase(),
            ) { sessionUiState, bookmarkIds ->
                when (sessionUiState) {
                    is SessionDetailUiState.Loading -> sessionUiState
                    is SessionDetailUiState.Success -> {
                        sessionUiState.copy(bookmarked = bookmarkIds.contains(sessionUiState.pokemonRes.id.toString()))
                    }
                }
            }.collect { _sessionUiState.value = it }
        }
    }

    fun fetchPokemon(enName: String) {
        viewModelScope.launch {
            val session = getPokemonUseCase(enName)
            _sessionUiState.value = SessionDetailUiState.Success(session)
        }
    }

    fun toggleBookmark() {
        val uiState = sessionUiState.value
        if (uiState !is SessionDetailUiState.Success) {
            return
        }
        viewModelScope.launch {
            val bookmark = uiState.bookmarked
            bookmarkSessionUseCase(uiState.pokemonRes.id.toString(), !bookmark)
            _sessionUiEffect.value = SessionDetailEffect.ShowToastForBookmarkState(!bookmark)
        }
    }

    fun hidePopup() {
        viewModelScope.launch {
            _sessionUiEffect.value = SessionDetailEffect.Idle
        }
    }
}
