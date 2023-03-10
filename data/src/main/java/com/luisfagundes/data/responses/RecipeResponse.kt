package com.luisfagundes.data.responses

data class RecipeResponse(
    val id: Int,
    val title: String,
    val image: String,
    val servings: Int,
    val readyInMinutes: Int,
    val sourceUrl: String?,
    val aggregateLikes: Int,
    val spoonacularScore: Int,
    val sourceName: String?,
    val healthScore: Int,
    val cheap: Boolean,
    val extendedIngredients: List<IngredientResponse>?,
    val vegetarian: Boolean,
    val vegan: Boolean,
    val dishTypes: List<String>?,
    val summary: String,
    val glutenFree: Boolean? = null,
    val dairyFree: Boolean? = null,
    val veryHealthy: Boolean? = null,
    val veryPopular: Boolean? = null,
    val sustainable: Boolean? = null,
    val analyzedInstructions: List<InstructionResponse>
)