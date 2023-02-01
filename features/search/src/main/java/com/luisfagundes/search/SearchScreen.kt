package com.luisfagundes.search

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.small),
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
