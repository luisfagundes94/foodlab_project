package com.luisfagundes.component.widget.searchbar

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.luisfagundes.component.R
import com.luisfagundes.component.widget.autocomplete.AutoCompleteBox
import com.luisfagundes.component.widget.autocomplete.AutoCompleteSearchBarTag
import com.luisfagundes.component.widget.autocomplete.asAutoCompleteEntities

@ExperimentalAnimationApi
@Composable
fun SearchWithAutoComplete(
    items: List<String>,
    onItemClicked: (String) -> Unit,
    onQueryChanged: (String) -> Unit,
    isUserSearching: (Boolean) -> Unit
) {
    val autoCompleteEntities = items.asAutoCompleteEntities(
        filter = { item, query ->
            item.lowercase().startsWith(query.lowercase())
        }
    )

    AutoCompleteBox(
        items = autoCompleteEntities,
        itemContent = { item ->
            ValueAutoCompleteItem(item.value)
        }
    ) {
        var value by remember { mutableStateOf("") }
        val view = LocalView.current

        onItemSelected { item ->
            value = item.value
            onItemClicked(item.value)
            view.clearFocus()
        }

        TextSearchBar(
            modifier = Modifier
                .testTag(AutoCompleteSearchBarTag)
                .onFocusChanged {
                    isSearching = it.isFocused
                    isUserSearching(isSearching)
                },
            value = value,
            label = stringResource(R.string.search_recipes),
            onDoneActionClick = {
                view.clearFocus()
            },
            onClearClick = {
                value = ""
                view.clearFocus()
            },
            onValueChanged = { query ->
                value = query
                onQueryChanged(query)
            }
        )
    }
}

@Composable
fun ValueAutoCompleteItem(item: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = item, style = MaterialTheme.typography.titleMedium)
    }
}