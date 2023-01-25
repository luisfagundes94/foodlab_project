package com.luisfagundes.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.recipes.R
import com.luisfagundes.theme.spacing
import kotlin.math.roundToInt

@Composable
fun NutritionFacts(recipe: Recipe) {
    Column {
        Divider(
            modifier = Modifier.padding(vertical = MaterialTheme.spacing.small),
            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            val fat = recipe.nutrition.caloricBreakDown.percentFat.roundToInt()
            val protein = recipe.nutrition.caloricBreakDown.percentProtein.roundToInt()
            val carbs = recipe.nutrition.caloricBreakDown.percentCarbs.roundToInt()

            NutritionFactItem(stringResource(R.string.fat), "$fat")
            NutritionFactItem(stringResource(R.string.protein), "$protein")
            NutritionFactItem(stringResource(R.string.carbs), "$carbs")
        }
        Divider(
            modifier = Modifier.padding(vertical = MaterialTheme.spacing.small),
            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        )
    }
}

@Composable
private fun NutritionFactItem(
    name: String,
    amount: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = amount,
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "%",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }
        Text(
            text = name
        )
    }
}

