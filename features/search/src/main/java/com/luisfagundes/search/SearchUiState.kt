package com.luisfagundes.search

import com.luisfagundes.domain.models.RecipeAutoComplete

data class SearchUiState(
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val queryResult: List<RecipeAutoComplete> =  emptyList()
)
