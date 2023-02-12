package com.luisfagundes.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.navigationBarsHeight
import com.luisfagundes.component.widget.Cuisines
import com.luisfagundes.component.widget.searchbar.SearchWithAutoComplete
import com.luisfagundes.domain.models.RecipeAutoComplete
import com.luisfagundes.theme.spacing
import com.ramcosta.composedestinations.annotation.Destination

@Destination(start = true)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var currentQuery by remember { mutableStateOf("") }
    val navigationBarHeight = 80.dp

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = MaterialTheme.spacing.extraSmall,
            )
            .padding(
                bottom = navigationBarHeight
            )
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RecipeSearchBar(
            autoCompleteList = uiState.queryResult,
            onQueryChanged = { newQuery ->
                currentQuery = newQuery
                viewModel.fetchRecipeAutoComplete(currentQuery)
            },
            isUserSearching = { isSearching ->
                //TODO toggle uiState
            }
        )
        Spacer(modifier = Modifier.padding(bottom = MaterialTheme.spacing.small))
        Cuisines()
    }
}

@Composable
fun RecipeSearchBar(
    autoCompleteList: List<RecipeAutoComplete>,
    onQueryChanged: (String) -> Unit,
    isUserSearching: (Boolean) -> Unit
) {
    val recipeNames = autoCompleteList.map { it.title }

    SearchWithAutoComplete(
        items = recipeNames,
        onItemClicked = {},
        onQueryChanged = onQueryChanged,
        isUserSearching = isUserSearching
    )
}
