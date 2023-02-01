package com.luisfagundes.data.mappers

import com.luisfagundes.data.responses.RecipeAutoCompleteResponse
import com.luisfagundes.domain.models.RecipeAutoComplete

object RecipeAutoCompleteMapper {
    fun List<RecipeAutoCompleteResponse>.toDomainModel() =
        this.map { it.toDomain() }

    private fun RecipeAutoCompleteResponse.toDomain() = RecipeAutoComplete(
        id = this.id,
        title = this.title
    )
}