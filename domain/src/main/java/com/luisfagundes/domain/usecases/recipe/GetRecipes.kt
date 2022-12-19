package com.luisfagundes.domain.usecases.recipe

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.domain.repositories.RecipeRepository
import com.luisfagundes.framework.base.FlowPagingUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecipes @Inject constructor(
    private val repository: RecipeRepository
) : FlowPagingUseCase<GetRecipes.Params, Recipe>() {

    data class Params(
        val pagingConfig: PagingConfig,
        val params: Map<String, String>
    )

    override fun execute(params: Params): Flow<PagingData<Recipe>> {
        return Pager(
            config = params.pagingConfig,
            pagingSourceFactory = { repository.fetchRecipes(params.params) }
        ).flow
    }
}