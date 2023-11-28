package com.example.sdutest.core.domain.usecase

import com.example.sdutest.core.data.repository.SessionRepository
import com.example.sdutest.core.model.Session
import javax.inject.Inject

class GetSessionUseCase @Inject constructor(
    private val sessionRepository: SessionRepository,
) {

    suspend operator fun invoke(sessionId: String): Session {
        return sessionRepository.getSession(sessionId)
    }
}
