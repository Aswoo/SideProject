package com.example.sdutest.core.domain.usecase

import com.example.sdutest.core.data.repository.SessionRepository
import com.example.sdutest.core.model.Session
import javax.inject.Inject

class GetSessionsUseCase @Inject constructor(
    private val sessionRepository: SessionRepository,
) {
    suspend operator fun invoke(): List<Session> {
        val data = sessionRepository.getSessions()
        return data
    }
    suspend fun wtf() : List<Session> {
        return sessionRepository.getSessions()
    }
}
