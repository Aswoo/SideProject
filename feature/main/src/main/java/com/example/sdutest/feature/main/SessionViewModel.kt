package com.example.sdutest.feature.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sdutest.core.domain.usecase.GetBookmarkedSessionIdsUseCase
import com.example.sdutest.core.domain.usecase.GetSessionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(
    private val getSessionsUseCase: GetSessionsUseCase,
    private val getBookmarkedSessionIdsUseCase: GetBookmarkedSessionIdsUseCase,
) : ViewModel() {

    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow: SharedFlow<Throwable> get() = _errorFlow

    private val _uiState = MutableStateFlow<SessionUiState>(SessionUiState.Loading)
    val uiState: StateFlow<SessionUiState> = _uiState.asStateFlow()

    fun hi(){
        viewModelScope.launch {
            val list = getSessionsUseCase.wtf()
        }
    }


    init {
        viewModelScope.launch {
            val sessionsFlow = flow { emit(getSessionsUseCase()) }
            val bookmarkedIdsFlow = getBookmarkedSessionIdsUseCase()

            val list = getSessionsUseCase.wtf()

            Log.d("HI","dd")

            sessionsFlow.combine(bookmarkedIdsFlow) { sessions, bookmarkedIds ->
                                              val enhancedSessions = sessions.map { session ->
                    session.copy(isBookmarked = bookmarkedIds.contains(session.id))
                }
                SessionUiState.Sessions(
                    sessions = enhancedSessions.toPersistentList()
                )
            }.catch { throwable ->
                _errorFlow.emit(throwable)
            }.collect { combinedUiState ->
                Log.d("CIVAL",combinedUiState.sessions.toString())
                _uiState.value = combinedUiState
            }
        }
    }
}
