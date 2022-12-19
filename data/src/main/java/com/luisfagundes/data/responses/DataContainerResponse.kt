package com.luisfagundes.data.responses

data class DataContainerResponse(
    val offset: Int,
    val number: Int,
    val totalResults: Int,
    val results: List<RecipeResponse>
)