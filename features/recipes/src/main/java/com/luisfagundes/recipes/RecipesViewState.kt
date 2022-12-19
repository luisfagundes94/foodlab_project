package com.luisfagundes.recipes

import androidx.paging.PagingData
import com.luisfagundes.domain.models.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class RecipesViewState(
    val pagedData: Flow<PagingData<Recipe>> = emptyFlow(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isEmpty: Boolean = false
)