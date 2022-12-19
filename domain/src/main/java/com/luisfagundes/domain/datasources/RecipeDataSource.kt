package com.luisfagundes.domain.datasources

import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.domain.models.RecipeListBody

interface RecipeDataSource {
    suspend fun fetchRecipes(queries: HashMap<String, String>): RecipeListBody
    suspend fun fetchRecipeDetails(id: Int): Recipe
}