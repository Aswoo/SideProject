package com.example.sdutest.feature.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sdutest.core.designsystem.theme.KnightsTheme
import com.example.sdutest.core.model.PokeSession
import com.example.sdutest.core.model.Room
import com.example.sdutest.core.model.Session
import com.example.sdutest.core.ui.RoomText
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun SessionScreen(
    onBackClick: () -> Unit,
    onSessionClick: (PokeSession) -> Unit,
    onShowSnackbar: suspend (String, String?) -> Boolean,
    sessionUiState: SessionUiState
) {
//    val sessionUiState by sessionViewModel.uiState.collectAsStateWithLifecycle()
    val sessionState = (sessionUiState as? SessionUiState.Sessions)?.sessions?.let { sessions ->
        rememberSessionState(sessions = sessions) // SessionUiState.Sessions
    }
        ?: rememberSessionState(sessions = persistentListOf()) // SessionUiState.Loading, SessionUiState.Error

//    LaunchedEffect(true) {
//        sessionViewModel.errorFlow.collectLatest { throwable -> onShowErrorSnackBar(throwable) }
//    }

    Box(modifier = Modifier.fillMaxSize()) {
        SessionTopAppBar(
            sessionState = sessionState,
            onBackClick = onBackClick,
        )
        SessionContent(
            sessionState = sessionState,
            modifier = Modifier
                .systemBarsPadding()
                .padding(top = 48.dp)
                .fillMaxSize(),
            onSessionClick = onSessionClick,
        )
    }
}

@Composable
private fun SessionContent(
    sessionState: SessionState,
    onSessionClick: (PokeSession) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        state = sessionState.listState,
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        sessionItems(
            items = sessionState.sessions,
            topPadding = SessionGroupSpace,
            onItemClick = onSessionClick,
        )
//        sessionState.sessions.forEachIndexed { index, sessions ->
//            val topPadding = if (index == 0) {
//                SessionTopSpace
//            } else {
//                SessionGroupSpace
//            }
//
//        }
    }
}

private val SessionTopSpace = 4.dp
private val SessionGroupSpace = 16.dp

private fun LazyListScope.sessionItems(
    items: PersistentList<PokeSession>,
    topPadding: Dp,
    onItemClick: (PokeSession) -> Unit,
) {
    itemsIndexed(items) { index, item ->
        SessionItem(
            index = index,
            item = item,
            topPadding = topPadding,
            onItemClick = onItemClick
        )
    }
}

@Composable
private fun SessionItem(
    index: Int,
    item: PokeSession,
    topPadding: Dp,
    onItemClick: (PokeSession) -> Unit,
) {
    Column {
        SessionCard(session = item, onSessionClick = onItemClick)
    }
}

@Composable
private fun RoomTitle(
    room: Room,
    topPadding: Dp,
) {
    Column(modifier = Modifier.padding(start = 20.dp, top = topPadding, end = 20.dp)) {
        RoomText(
            room = room,
            style = KnightsTheme.typography.titleLargeB,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Divider(thickness = 2.dp, color = MaterialTheme.colorScheme.onPrimaryContainer)
        Spacer(modifier = Modifier.height(32.dp))
    }
}
