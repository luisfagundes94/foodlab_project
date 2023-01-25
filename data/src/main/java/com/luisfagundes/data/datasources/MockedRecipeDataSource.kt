package com.luisfagundes.data.datasources

import android.content.Context
import com.google.gson.Gson
import com.luisfagundes.data.mappers.RecipeMapper.toDomainModel
import com.luisfagundes.data.responses.DataContainerResponse
import com.luisfagundes.data.responses.RecipeResponse
import com.luisfagundes.data.utils.getJsonDataFromAsset
import com.luisfagundes.domain.datasources.RecipeDataSource
import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.domain.models.RecipeListBody

class MockedRecipeDataSource(
    private val appContext: Context
) : RecipeDataSource {
    override suspend fun fetchRecipes(queries: Map<String, String>): RecipeListBody {
        val jsonFile = getJsonDataFromAsset(appContext, "recipes.json")
        val gson = Gson()
        val data = gson.fromJson(jsonFile, DataContainerResponse::class.java)
        val recipes = data.results.map { it.toDomainModel() }

        return RecipeListBody(
            data.offset,
            data.totalResults,
            recipes
        )
    }

    override suspend fun fetchRecipeDetails(id: Int): Recipe {
        val jsonFile = getJsonDataFromAsset(appContext, "recipe_detail.json")
        val gson = Gson()
        val data = gson.fromJson(jsonFile, RecipeResponse::class.java)
        return data.toDomainModel()
    }
}