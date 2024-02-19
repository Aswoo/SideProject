package com.example.sdutest.feature.main

import app.cash.turbine.test
import com.example.sdutest.core.domain.usecase.GetBookmarkedSessionIdsUseCase
import com.example.sdutest.core.domain.usecase.GetSessionsUseCase
import com.example.sdutest.core.model.PokeSession
import com.example.sdutest.feature.main.SessionViewModel
import com.example.testing.rule.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDateTime
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

internal class SessionViewModelTest {
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val getSessionsUseCase: GetSessionsUseCase = mockk()
    private val getBookmarkedSessionIdsUseCase: GetBookmarkedSessionIdsUseCase = mockk()
    private lateinit var viewModel: SessionViewModel

    private val fakeSession = PokeSession(
        id = "1",
        name = "title",
        content = "content",
        height = 1f,
        weight = 2f,
        types = listOf("풀","독"),
        category = "이상해",
        sex = listOf("남","여"),
        image = "fake_url",
        enName = "burgal",
        isBookmarked = false
    )

    @Test
    fun `포켓 세션 데이터를 확인할 수 있다`() = runTest {
        // given
        coEvery { getSessionsUseCase() } returns listOf(fakeSession)
        coEvery { getBookmarkedSessionIdsUseCase() } returns flowOf(emptySet())
        viewModel = SessionViewModel(getSessionsUseCase, getBookmarkedSessionIdsUseCase)

        // when & then
        viewModel.uiState.test {
            val actual = (awaitItem() as? SessionUiState.Sessions)?.sessions?.first()
            assertEquals(fakeSession, actual)
        }
    }
}
