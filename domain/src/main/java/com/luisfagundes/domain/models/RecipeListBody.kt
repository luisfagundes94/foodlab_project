package com.luisfagundes.domain.models

data class RecipeListBody(
    val offset: Int,
    val totalResults: Int,
    val results: List<Recipe>
)