package com.luisfagundes.data.datasources

import com.luisfagundes.data.mappers.RecipeMapper.toDomainModel
import com.luisfagundes.data.services.ApiService
import com.luisfagundes.domain.datasources.RecipeDataSource
import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.domain.models.RecipeListBody

class RemoteRecipeDataSource(
    private val apiService: ApiService
) : RecipeDataSource {
    override suspend fun fetchRecipes(queries: Map<String, String>): RecipeListBody {
        val data = apiService.fetchRecipes(queries)
        val recipes = data.results.map { it.toDomainModel() }

        return RecipeListBody(
            data.offset,
            data.totalResults,
            recipes
        )
    }

    override suspend fun fetchRecipeDetails(id: Int): Recipe {
        val data = apiService.fetchRecipeDetails(id)
        return data.toDomainModel()
    }
}