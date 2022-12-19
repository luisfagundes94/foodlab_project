package com.luisfagundes.domain.repositories

import kotlinx.coroutines.flow.Flow

interface WelcomeRepository {
    suspend fun saveOnBoardingState(completed: Boolean)
    fun readOnBoardingState(): Flow<Boolean>
}