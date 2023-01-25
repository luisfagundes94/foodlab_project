package com.luisfagundes.component.widget

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.luisfagundes.component.R
import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.theme.spacing

@Composable
fun RecipeTags(recipe: Recipe) {
    val recipeTags = createTagsList(recipe)

    LazyRow(
        modifier = Modifier.padding(bottom = MaterialTheme.spacing.small)
    ) {
        items(
            recipeTags.filter {
                it.first == true
            }
        ) { tag ->
            ChipView(chipMessage = tag.second)
        }
    }
}

@Composable
private fun createTagsList(recipe: Recipe) = listOf(
    Pair(
        recipe.cheap,
        stringResource(id = R.string.cheap)
    ),
    Pair(
        recipe.glutenFree,
        stringResource(id = R.string.glutenFree)
    ),
    Pair(
        recipe.vegetarian,
        stringResource(id = R.string.vegetarian)
    ),
    Pair(
        recipe.vegan,
        stringResource(id = R.string.vegan)
    ),
    Pair(
        recipe.dairyFree,
        stringResource(id = R.string.dairyFree)
    ),
    Pair(
        recipe.veryHealthy,
        stringResource(id = R.string.veryHealthy)
    ),
    Pair(
        recipe.veryPopular,
        stringResource(id = R.string.veryPopular)
    ),
    Pair(
        recipe.sustainable,
        stringResource(id = R.string.sustainable)
    )
)