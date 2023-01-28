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
import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.recipes.R
import com.luisfagundes.theme.spacing

@Composable
fun RecipeFacts(recipe: Recipe) {
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
            RecipeFactItem(
                name = stringResource(R.string.servings),
                amount = "${recipe.servings}"
            )
            RecipeFactItem(
                name = stringResource(R.string.readyInMinutes),
                amount = "${recipe.readyInMinutes}"
            )
            RecipeFactItem(
                name = stringResource(R.string.aggregateLikes),
                amount = "${recipe.aggregateLikes}"
            )
        }
        Divider(
            modifier = Modifier.padding(vertical = MaterialTheme.spacing.small),
            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        )
    }
}

@Composable
private fun RecipeFactItem(
    name: String,
    amount: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = amount,
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = name
        )
    }
}

