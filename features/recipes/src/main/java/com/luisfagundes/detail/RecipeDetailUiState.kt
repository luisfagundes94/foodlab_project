package com.luisfagundes.detail

import com.luisfagundes.domain.models.Recipe

data class RecipeDetailUiState(
    val recipe: Recipe? = null,
    val isLoading: Boolean = false,
    val hasError: Boolean = false
)
