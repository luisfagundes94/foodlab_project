package com.luisfagundes.data.mappers

import com.luisfagundes.data.mappers.IngredientMapper.mapToDomain
import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.extensions.empty
import com.luisfagundes.data.responses.RecipeResponse

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
            ingredients = this.extendedIngredients?.mapToDomain() ?: emptyList()
        )
}