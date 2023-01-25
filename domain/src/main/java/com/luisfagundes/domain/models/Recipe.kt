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
    val ingredients: List<Ingredient>?,
    val vegetarian: Boolean,
    val vegan: Boolean,
    val dishTypes: List<String>,
    val summary: String,
    val sourceName: String,
    val glutenFree: Boolean?,
    val dairyFree: Boolean?,
    val veryHealthy: Boolean?,
    val veryPopular: Boolean?,
    val sustainable: Boolean?,
    val nutrition: Nutrition,
)

data class Nutrition(
    val nutrients: List<Nutrient>,
    val caloricBreakDown: CaloricBreakDown
)

data class Nutrient(
    val name: String,
    val amount: Float,
    val unit: String,
    val percentOfDailyNeeds: Float
)

data class CaloricBreakDown(
    val percentProtein: Float,
    val percentFat: Float,
    val percentCarbs: Float
)
