package com.luisfagundes.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.luisfagundes.domain.models.Ingredient
import com.luisfagundes.theme.spacing

@Composable
fun RecipeIngredients(ingredients: List<Ingredient>) {
    ingredients.forEach {
        Ingredient(ingredient = it)
    }
}

@Composable
private fun Ingredient(ingredient: Ingredient) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val isChecked = remember { mutableStateOf(false) }

        Checkbox(
            checked = isChecked.value,
            onCheckedChange = { isChecked.value = it }
        )
        Text(text = ingredient.originalMeasure)
    }
}