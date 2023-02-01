package com.luisfagundes.data.repositories

import androidx.paging.PagingSource
import com.luisfagundes.domain.datasources.RecipeDataSource
import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.data.paging.RecipePagingSource
import com.luisfagundes.domain.models.RecipeAutoComplete
import com.luisfagundes.domain.repositories.RecipeRepository
import kotlinx.coroutines.flow.Flow

class RecipeRepositoryImpl(
    private val remoteDataSource: RecipeDataSource
) : RecipeRepository {
    override fun fetchRecipes(queryMap: Map<String, String>): PagingSource<Int, Recipe> {
        return RecipePagingSource(remoteDataSource, queryMap)
    }

    override suspend fun fetchRecipeDetails(id: Int): Recipe {
        return remoteDataSource.fetchRecipeDetails(id)
    }

    override suspend fun fetchRecipeAutoComplete(query: String): List<RecipeAutoComplete> {
        return remoteDataSource.fetchRecipesAutoComplete(query)
    }
}