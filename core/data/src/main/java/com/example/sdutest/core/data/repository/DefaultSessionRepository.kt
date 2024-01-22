package com.example.sdutest.core.data.repository


import JvmUnitTestFakeAssetManager
import android.util.Log
import com.example.sdutest.core.common.network.Dispatcher
import com.example.sdutest.core.common.network.NiaDispatchers
import com.example.sdutest.core.data.api.GithubRawApi
import com.example.sdutest.core.data.api.fake.FakeAssetManager
import com.example.sdutest.core.data.mapper.toData
import com.example.sdutest.core.data.model.SessionResponse
import com.example.sdutest.core.datastore.datasource.SessionPreferencesDataSource
import com.example.sdutest.core.model.PokeSession
import com.example.sdutest.core.model.Session
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import javax.inject.Inject

public class DefaultSessionRepository @Inject constructor(
    @Dispatcher(NiaDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val networkJson: Json,
    private val sessionDataSource: SessionPreferencesDataSource,
    private val assets: FakeAssetManager,
) : SessionRepository {
    private var cachedSessions: List<PokeSession> = emptyList()

    private val bookmarkIds: Flow<Set<String>> = sessionDataSource.bookmarkedSession

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getSessions(): List<PokeSession> {
        return withContext(ioDispatcher) {
            val data = assets.open(TOPICS_ASSET)
            data.use(networkJson::decodeFromStream)
        }
    }

    override suspend fun getSession(sessionId: String): PokeSession {
        val cachedSession = cachedSessions.find { it.id == sessionId }
        if (cachedSession != null) {
            return cachedSession
        }

        return getSessions().find { it.id == sessionId }
            ?: error("Session not found with id: $sessionId")
    }

    override suspend fun getBookmarkedSessionIds(): Flow<Set<String>> {
        return bookmarkIds.filterNotNull()
    }

    override suspend fun bookmarkSession(sessionId: String, bookmark: Boolean) {
        val currentBookmarkedSessionIds = bookmarkIds.first()
        sessionDataSource.updateBookmarkedSession(
            if (bookmark) {
                currentBookmarkedSessionIds + sessionId
            } else {
                currentBookmarkedSessionIds - sessionId
            }
        )
    }

    companion object {
        private const val NEWS_ASSET = "news.json"
        private const val TOPICS_ASSET = "poke_sessions.json"
    }
}
