package com.example.sdutest.core.data.repository

import com.example.sdutest.core.model.PokeSession
import com.example.sdutest.core.model.Session
import kotlinx.coroutines.flow.Flow

interface SessionRepository {

    suspend fun getSessions(): List<PokeSession>

    suspend fun getSession(sessionId: String): PokeSession

    suspend fun getBookmarkedSessionIds(): Flow<Set<String>>

    suspend fun bookmarkSession(sessionId: String, bookmark: Boolean)
}
