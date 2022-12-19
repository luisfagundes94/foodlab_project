package com.luisfagundes.domain.mockdata

import com.luisfagundes.domain.models.Recipe

class RecipeFactory {
    fun create() = Recipe(
        id = RANDOM_NUMBER,
        title = TITLE,
        image = IMAGE,
        servings = RANDOM_NUMBER,
        readyInMinutes = RANDOM_NUMBER,
        sourceUrl = "",
        aggregateLikes = RANDOM_NUMBER,
        spoonacularScore = RANDOM_NUMBER,
        healthScore = RANDOM_NUMBER,
        cheap = false,
        ingredients = emptyList(),
        vegetarian = false,
        vegan = false,
        dishTypes = emptyList(),
        summary = "",
        sourceName = ""
    )

    private companion object {
        const val RANDOM_NUMBER = 1
        const val TITLE = "TITLE"
        const val IMAGE = "IMAGE"
    }
}