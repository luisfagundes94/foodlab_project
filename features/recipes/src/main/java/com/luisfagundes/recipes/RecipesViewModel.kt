package com.luisfagundes.recipes

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.domain.usecases.recipe.GetRecipes
import com.luisfagundes.framework.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val getRecipes: GetRecipes
) : BaseViewModel() {

    fun getRecipesPagingData(): Flow<PagingData<Recipe>>  {
        val params = GetRecipes.Params(
            pagingConfig = PagingConfig(pageSize = PAGE_SIZE, prefetchDistance = 2),
            params = mapOf(
                SORT to RANDOM,
                ADD_RECIPE_INFORMATION to true.toString(),
                LIMIT_LICENSE to true.toString(),
                INSTRUCTIONS_REQUIRED to true.toString()
            )
        )
        return getRecipes(params).cachedIn(scope = viewModelScope)
    }

    private companion object {
        const val PAGE_SIZE = 15
        const val SORT = "sort"
        const val RANDOM = "random"
        const val ADD_RECIPE_INFORMATION = "addRecipeInformation"
        const val LIMIT_LICENSE = "limitLicense"
        const val INSTRUCTIONS_REQUIRED = "instructionsRequired"
    }
}