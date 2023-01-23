package com.luisfagundes.domain.usecases.recipe

import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.domain.repositories.RecipeRepository
import com.luisfagundes.framework.base.IoDispatcher
import com.luisfagundes.framework.base.SuspendUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetRecipeDetail @Inject constructor(
    private val repository: RecipeRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
): SuspendUseCase<GetRecipeDetail.Params, Recipe>(ioDispatcher) {

    data class Params(
        val id: Int
    )

    override suspend fun execute(parameters: Params) =
        repository.fetchRecipeDetails(parameters.id)
}