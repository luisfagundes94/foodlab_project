package com.luisfagundes.data.mappers

import com.luisfagundes.data.responses.CaloricBreakDownResponse
import com.luisfagundes.data.responses.NutrientResponse
import com.luisfagundes.data.responses.NutritionResponse
import com.luisfagundes.domain.models.CaloricBreakDown
import com.luisfagundes.domain.models.Nutrient
import com.luisfagundes.domain.models.Nutrition

object NutritionMapper {
    fun NutritionResponse.mapToDomain() = Nutrition(
        nutrients = this.nutrients.map { it.toDomain() },
        caloricBreakDown = this.caloricBreakdown.toDomain()
    )

    private fun NutrientResponse.toDomain() = Nutrient(
        unit = this.unit,
        name = this.name,
        amount = this.amount,
        percentOfDailyNeeds = this.percentOfDailyNeeds
    )

    private fun CaloricBreakDownResponse.toDomain() = CaloricBreakDown(
        percentFat = this.percentFat,
        percentCarbs = this.percentCarbs,
        percentProtein = this.percentProtein
    )
}