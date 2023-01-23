package com.luisfagundes.detail

import androidx.lifecycle.SavedStateHandle
import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.domain.usecases.recipe.GetRecipeDetail
import com.luisfagundes.framework.base.BaseViewModel
import com.luisfagundes.framework.network.DataState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

const val RECIPE_ID = "recipeId"
private const val DEFAULT_RECIPE_ID = -1

class RecipeDetailViewModel(
    private val getRecipeDetail: GetRecipeDetail,
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val recipeId: Int = savedStateHandle[RECIPE_ID] ?: DEFAULT_RECIPE_ID

    private val _uiState = MutableStateFlow(RecipeDetailUiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchRecipeDetails(recipeId)
    }

    private fun fetchRecipeDetails(id: Int) = safeLaunch {
        val dataState = getRecipeDetail(GetRecipeDetail.Params(id))
        handleDataState(dataState)
    }

    private fun handleDataState(dataState: DataState<Recipe>) {
        when (dataState) {
            is DataState.Success -> _uiState.update {
                it.copy(recipe = dataState.result)
            }
            is DataState.Error -> _uiState.update {
                it.copy(error = dataState.error.localizedMessage)
            }
        }
    }

}