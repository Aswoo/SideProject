package com.example.sdutest.core.domain.usecase

import com.example.sdutest.core.model.PokeSession
import com.example.sdutest.core.model.Session
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetBookmarkedSessionsUseCase @Inject constructor(
    private val getSessionsUseCase: GetSessionsUseCase,
    private val getBookmarkedSessionIdsUseCase: GetBookmarkedSessionIdsUseCase
) {

    suspend operator fun invoke(): Flow<List<PokeSession>> {
        return flow {
            emit(getSessionsUseCase())
        }.combine(getBookmarkedSessionIdsUseCase()) { allSession, bookmarkedSessions ->
            allSession
                .filter { session -> bookmarkedSessions.contains(session.id) }
                .sortedBy { it.id }
        }
    }
}
