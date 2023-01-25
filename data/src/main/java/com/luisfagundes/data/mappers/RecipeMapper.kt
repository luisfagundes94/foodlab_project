package com.luisfagundes.data.mappers

import com.luisfagundes.data.mappers.IngredientMapper.mapToDomain
import com.luisfagundes.data.mappers.NutritionMapper.mapToDomain
import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.extensions.empty
import com.luisfagundes.data.responses.RecipeResponse
import com.luisfagundes.domain.models.CaloricBreakDown
import com.luisfagundes.domain.models.Nutrition

object RecipeMapper {
    fun RecipeResponse.toDomainModel() =
        Recipe(
            id = this.id,
            title = this.title,
            image = this.image,
            servings = this.servings,
            readyInMinutes = this.readyInMinutes,
            sourceUrl = this.sourceUrl,
            aggregateLikes = this.aggregateLikes,
            spoonacularScore = this.spoonacularScore,
            healthScore = this.healthScore,
            cheap = this.cheap,
            vegetarian = this.vegetarian,
            vegan = this.vegan,
            dishTypes = this.dishTypes ?: emptyList(),
            summary = this.summary,
            sourceName = this.sourceName ?: String.empty(),
            ingredients = this.extendedIngredients?.mapToDomain(),
            dairyFree = this.dairyFree,
            glutenFree = this.glutenFree,
            veryHealthy = this.veryHealthy,
            veryPopular = this.veryPopular,
            sustainable = this.sustainable,
            nutrition = this.nutrition?.mapToDomain() ?: Nutrition(
                emptyList(), CaloricBreakDown(
                    0f,
                    0f,
                    0f
                )
            )
        )
}