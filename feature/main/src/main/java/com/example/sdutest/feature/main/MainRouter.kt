package com.example.sdutest.feature.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sdutest.core.model.PokeSession
import com.example.sdutest.core.model.Session

@Composable
internal fun MainRouter(
    onBackClick: () -> Unit,
    onSessionClick: (PokeSession) -> Unit,
    onShowSnackbar: suspend (String, String?) -> Boolean,
    sessionViewModel: SessionViewModel = hiltViewModel(),
) {
    val sessionUiState by sessionViewModel.uiState.collectAsStateWithLifecycle()
    SessionScreen(
        onBackClick = onBackClick,
        onSessionClick = onSessionClick,
        onShowSnackbar = onShowSnackbar,
        sessionUiState = sessionUiState
    )
}