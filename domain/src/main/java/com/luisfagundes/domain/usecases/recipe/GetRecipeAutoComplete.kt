package com.luisfagundes.domain.usecases.recipe

import com.luisfagundes.domain.models.RecipeAutoComplete
import com.luisfagundes.domain.repositories.RecipeRepository
import com.luisfagundes.framework.base.IoDispatcher
import com.luisfagundes.framework.base.SuspendUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetRecipeAutoComplete @Inject constructor(
    private val repository: RecipeRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
): SuspendUseCase<GetRecipeAutoComplete.Params, List<RecipeAutoComplete>>(ioDispatcher) {

    data class Params(
        val query: String
    )

    override suspend fun execute(parameters: Params): List<RecipeAutoComplete> =
        if (parameters.query.length >= START_QUERY_LENGTH)
            repository.fetchRecipeAutoComplete(parameters.query)
        else
            emptyList()

    private companion object {
        const val START_QUERY_LENGTH = 2
    }
}