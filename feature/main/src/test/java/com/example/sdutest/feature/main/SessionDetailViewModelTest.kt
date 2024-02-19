package com.example.sdutest.feature.main

import app.cash.turbine.test
import com.example.sdutest.core.domain.usecase.BookmarkSessionUseCase
import com.example.sdutest.core.domain.usecase.GetBookmarkedSessionIdsUseCase
import com.example.sdutest.core.domain.usecase.GetPokemonUseCase
import com.example.sdutest.core.model.pokemon.PokemonResponse
import com.example.testing.rule.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertIs
import kotlin.test.assertTrue

class SessionDetailViewModelTest {
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val getPokeSessionUseCase: GetPokemonUseCase = mockk()
    private val getBookmarkedSessionIdsUseCase: GetBookmarkedSessionIdsUseCase = mockk()
    private val bookmarkSessionUseCase: BookmarkSessionUseCase = mockk()
    private lateinit var viewModel: SessionDetailViewModel

    private val fakeSession = PokemonResponse(
        id = 1,
        weight = 2,
        name = "ditto",
    )

    @Before
    fun setup() {
        coEvery { getBookmarkedSessionIdsUseCase() } returns flowOf(emptySet())
    }

    @Test
    fun `포켓몬 데이터를 확인할 수 있다`() = runTest {
        // given
        val sessionId = "ditto"
        coEvery { getPokeSessionUseCase(sessionId) } returns fakeSession
        viewModel = SessionDetailViewModel(
            getPokeSessionUseCase,
            getBookmarkedSessionIdsUseCase,
            bookmarkSessionUseCase
        )

        // when
        viewModel.fetchPokemon(sessionId)

        // then
        viewModel.sessionUiState.test {
            val actual = awaitItem()
            assertIs<SessionDetailUiState.Success>(actual)
        }
    }

    @Test
    fun `포켓몬 북마크 여부를 확인할 수 있다`() = runTest {
        // given
        val enName = "ditto"
        coEvery { getPokeSessionUseCase(enName) } returns fakeSession
        coEvery { getBookmarkedSessionIdsUseCase() } returns flowOf(setOf(enName))
        viewModel = SessionDetailViewModel(
            getPokeSessionUseCase,
            getBookmarkedSessionIdsUseCase,
            bookmarkSessionUseCase
        )

        // when
        viewModel.fetchPokemon(enName)

        // then
        viewModel.sessionUiState.test {
            val actual = awaitItem() as SessionDetailUiState.Success
            assertTrue(actual.bookmarked)
        }
    }

    @Test
    fun `세션의 북마크 여부를 변경할 수 있다`() = runTest {
        // given
        val sessionId = "ditto"
        coEvery { getPokeSessionUseCase(sessionId) } returns fakeSession

        val flow = MutableStateFlow(emptySet<String>())
        coEvery { getBookmarkedSessionIdsUseCase() } returns flow
        coEvery { bookmarkSessionUseCase(sessionId, true) } answers {
            flow.update { it + sessionId }
        }

        viewModel = SessionDetailViewModel(
            getPokeSessionUseCase,
            getBookmarkedSessionIdsUseCase,
            bookmarkSessionUseCase
        )
        viewModel.fetchPokemon(sessionId)

        // when
        viewModel.toggleBookmark()

        // then
        viewModel.sessionUiState.test {
            val actual = awaitItem() as SessionDetailUiState.Success
            assertTrue(actual.bookmarked)
        }
    }
}
