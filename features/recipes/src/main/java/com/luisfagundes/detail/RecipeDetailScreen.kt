package com.luisfagundes.detail

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.luisfagundes.theme.spacing
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.luisfagundes.recipes.R
import com.luisfagundes.theme.FoodlabTheme

@OptIn(ExperimentalLifecycleComposeApi::class)
@Destination(start = true)
@Composable
fun RecipeDetailScreen(
    id: Int = 0,
    navigator: DestinationsNavigator,
    viewModel: RecipeDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    RecipeInformation()
}

@Composable
private fun RecipeInformation() {
    Column(
        modifier = Modifier.padding(MaterialTheme.spacing.default)
    ) {
        Image(url = "")
        Title(text = stringResource(R.string.about))
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun Image(url: String) {
    Card(
        onClick = {},
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                modifier = Modifier.height(200.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(url)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(R.string.recipe_desc),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun Title(
    text: String
) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.SemiBold
    )
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun RecipeDetailPreview() {
    FoodlabTheme {
        Surface {
            RecipeInformation()
        }
    }

}