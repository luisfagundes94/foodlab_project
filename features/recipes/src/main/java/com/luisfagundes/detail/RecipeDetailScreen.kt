package com.luisfagundes.detail

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.luisfagundes.component.widget.ErrorView
import com.luisfagundes.component.widget.HtmlText
import com.luisfagundes.component.widget.RecipeTags
import com.luisfagundes.domain.mockdata.RecipeFactory
import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.recipes.R
import com.luisfagundes.theme.FoodlabTheme
import com.luisfagundes.theme.spacing
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@Composable
fun RecipeDetailScreen(
    id: Int = 0,
    navigator: DestinationsNavigator,
    viewModel: RecipeDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    CheckUiState(uiState)

    LaunchedEffect(key1 = viewModel, block = {
        viewModel.fetchRecipeDetails(id)
    })
}

@Composable
private fun CheckUiState(uiState: RecipeDetailUiState) {
    when {
        uiState.recipe != null -> RecipeInformation(recipe = uiState.recipe)
        uiState.isLoading -> Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
        uiState.hasError -> ErrorView()
    }
}

@Composable
private fun RecipeInformation(
    recipe: Recipe
) {
    Column(
        modifier = Modifier
            .padding(MaterialTheme.spacing.default)
            .verticalScroll(rememberScrollState())
    ) {
        RecipeImage(url = recipe.image)
        VerticalDivider(MaterialTheme.spacing.verySmall)
        Title(text = recipe.title)
        RecipeTags(recipe)
        NutritionFacts(recipe = recipe)
        Title(text = stringResource(id = R.string.about))
        HtmlText(text = recipe.summary)
        VerticalDivider(MaterialTheme.spacing.verySmall)
        recipe.ingredients?.let { ingredients ->
            Title(text = stringResource(R.string.ingredients))
            RecipeIngredients(ingredients)
        }
        Title(text = stringResource(R.string.steps))
    }
}

@Composable
fun Title(
    text: String
) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.SemiBold
    )
    VerticalDivider(MaterialTheme.spacing.verySmall)
}

@Composable
fun VerticalDivider(spacing: Dp) {
    Spacer(modifier = Modifier.padding(vertical = spacing))
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun RecipeDetailPreview() {
    FoodlabTheme {
        Surface {
            val recipe = RecipeFactory.create()
            RecipeInformation(recipe)
        }
    }
}