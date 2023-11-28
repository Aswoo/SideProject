package com.example.sdutest.core.domain.usecase

import com.example.sdutest.core.data.repository.SessionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBookmarkedSessionIdsUseCase @Inject constructor(
    private val sessionRepository: SessionRepository,
) {

    suspend operator fun invoke(): Flow<Set<String>> {
        return sessionRepository.getBookmarkedSessionIds()
    }
}
