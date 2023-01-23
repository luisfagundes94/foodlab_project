package com.luisfagundes.domain.usecases.welcome

import com.luisfagundes.domain.repositories.WelcomeRepository
import com.luisfagundes.framework.base.FlowUseCase
import com.luisfagundes.framework.network.DataState
import javax.inject.Inject

class SaveOnBoarding @Inject constructor(
    private val repository: WelcomeRepository
): FlowUseCase<SaveOnBoarding.Params, Unit>() {

    data class Params(
        val isCompleted: Boolean
    )

    override suspend fun execute(params: Params): DataState<Unit> {
        repository.saveOnBoardingState(params.isCompleted)
        return DataState.Success(Unit)
    }
}