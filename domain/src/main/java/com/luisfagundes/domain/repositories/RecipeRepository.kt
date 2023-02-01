package com.luisfagundes.domain.repositories

import androidx.paging.PagingSource
import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.domain.models.RecipeAutoComplete

interface RecipeRepository {
    fun fetchRecipes(queryMap: Map<String, String>): PagingSource<Int, Recipe>
    suspend fun fetchRecipeDetails(id: Int): Recipe
    suspend fun fetchRecipeAutoComplete(query: String): List<RecipeAutoComplete>
}