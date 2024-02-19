package com.example.sdutest.feature.main

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import com.example.sdutest.core.model.PokeSession
import com.example.sdutest.core.model.Room
import com.example.sdutest.core.model.Session

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.mapNotNull


@Stable
class SessionState(
    val sessions: PersistentList<PokeSession>,
    val listState: LazyListState,
) {

//    var selectedRoom: Room? by mutableStateOf(selectedRoom)
//        private set

    val isAtTop by derivedStateOf {
        listState.firstVisibleItemIndex == 0 && listState.firstVisibleItemScrollOffset == 0
    }


//    fun select(room: Room) {
//        selectedRoom = room
//    }

    companion object {
        fun Saver(
            sessions: PersistentList<PokeSession>,
            listState: LazyListState,
        ): Saver<SessionState, *> = Saver(
            save = { Room.TRACK1 },
            restore = { selectedRoom ->
                SessionState(
                    sessions = sessions,
                    listState = listState,
                )
            }
        )
    }
}

@Composable
internal fun rememberSessionState(
    sessions: PersistentList<PokeSession>,
    listState: LazyListState = rememberLazyListState(),
): SessionState {
    val state = rememberSaveable(
        sessions,
        listState,
        saver = SessionState.Saver(sessions, listState),
    ) {
        SessionState(sessions, listState)
    }
    return state
}
