package com.luisfagundes.data.responses

data class IngredientResponse(
    val id: Int,
    val amount: Float,
    val name: String,
    val image: String?,
    val measures: MeasuresResponse
)