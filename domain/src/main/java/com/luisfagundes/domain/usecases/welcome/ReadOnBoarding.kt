package com.luisfagundes.domain.usecases.welcome

import com.luisfagundes.domain.repositories.WelcomeRepository
import com.luisfagundes.framework.base.ReturnUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadOnBoarding @Inject constructor(
    private val repository: WelcomeRepository
): ReturnUseCase<Unit, Boolean>() {

    override suspend fun execute(params: Unit): Flow<Boolean> {
        return repository.readOnBoardingState()
    }
}