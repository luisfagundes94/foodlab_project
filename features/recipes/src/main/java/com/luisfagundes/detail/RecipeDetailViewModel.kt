package com.luisfagundes.detail

import androidx.lifecycle.SavedStateHandle
import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.domain.usecases.recipe.GetRecipeDetail
import com.luisfagundes.framework.base.BaseViewModel
import com.luisfagundes.framework.network.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

const val RECIPE_ID = "recipeId"
private const val DEFAULT_RECIPE_ID = -1

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val getRecipeDetail: GetRecipeDetail,
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val recipeId: Int = savedStateHandle[RECIPE_ID] ?: DEFAULT_RECIPE_ID

    private val _uiState = MutableStateFlow(RecipeDetailUiState(isLoading = true))
    val uiState = _uiState.asStateFlow()

    fun fetchRecipeDetails(id: Int) = safeLaunch {
        val dataState = getRecipeDetail(GetRecipeDetail.Params(id))
        handleDataState(dataState)
    }

    private fun handleDataState(dataState: DataState<Recipe>) {
        when (dataState) {
            is DataState.Success -> _uiState.update {
                it.copy(recipe = dataState.result, isLoading = false)
            }
            is DataState.Error -> _uiState.update {
                it.copy(hasError = true, isLoading = false)
            }
        }
    }

}