package com.luisfagundes.data.factory

import com.luisfagundes.domain.mockdata.RecipeFactory
import com.luisfagundes.domain.models.RecipeListBody

object RecipeListBodyFactory {
    fun create() = RecipeListBody(
        offset = 0,
        totalResults = 3,
        results = listOf(
            RecipeFactory.create(),
            RecipeFactory.create(),
            RecipeFactory.create()
        )
    )
}