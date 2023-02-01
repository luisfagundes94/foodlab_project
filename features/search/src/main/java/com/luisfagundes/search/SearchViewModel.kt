package com.luisfagundes.search

import com.luisfagundes.domain.models.RecipeAutoComplete
import com.luisfagundes.domain.usecases.recipe.GetRecipeAutoComplete
import com.luisfagundes.framework.base.BaseViewModel
import com.luisfagundes.framework.network.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getRecipeAutoComplete: GetRecipeAutoComplete
): BaseViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    fun fetchRecipeAutoComplete(query: String) = safeLaunch {
        val dataState = getRecipeAutoComplete(GetRecipeAutoComplete.Params(query))
        handleDataState(dataState)
    }

    private fun handleDataState(dataState: DataState<List<RecipeAutoComplete>>) {
        when (dataState) {
            is DataState.Success -> _uiState.update {
                it.copy(queryResult = dataState.result, isLoading = false)
            }
            is DataState.Error -> _uiState.update {
                it.copy(hasError = true, isLoading = false)
            }
        }
    }
}