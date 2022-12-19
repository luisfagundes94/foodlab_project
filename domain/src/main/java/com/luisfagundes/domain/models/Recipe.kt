package com.luisfagundes.domain.models

data class Recipe(
    val id: Int,
    val title: String,
    val image: String,
    val servings: Int,
    val readyInMinutes: Int,
    val sourceUrl: String?,
    val aggregateLikes: Int,
    val spoonacularScore: Int,
    val healthScore: Int,
    val cheap: Boolean,
    val ingredients: List<Ingredient>,
    val vegetarian: Boolean,
    val vegan: Boolean,
    val dishTypes: List<String>,
    val summary: String,
    val sourceName: String
)
