package com.luisfagundes.recipes

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.luisfagundes.component.errorView
import com.luisfagundes.component.loadingView
import com.luisfagundes.domain.mockdata.RecipeFactory
import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.framework.flow.rememberFlowWithLifecycle
import com.luisfagundes.provider.NavigationProvider
import com.luisfagundes.theme.FoodlabTheme
import com.luisfagundes.theme.spacing
import kotlinx.coroutines.flow.Flow

@Composable
fun RecipesScreen(
    modifier: Modifier = Modifier,
    navigator: NavigationProvider,
    viewModel: RecipesViewModel = hiltViewModel()
) {
    Recipes(
        modifier = modifier,
        recipePagingData = viewModel.getRecipesPagingData(),
        onRecipeClick = { id -> navigator.openRecipeDetail(id) }
    )
}

@Composable
fun Recipes(
    modifier: Modifier = Modifier,
    recipePagingData: Flow<PagingData<Recipe>>,
    onRecipeClick: (Int) -> Unit = {}
) {
    val pagingItems = rememberFlowWithLifecycle(recipePagingData).collectAsLazyPagingItems()
    val isRefreshing = pagingItems.loadState.refresh == LoadState.Loading
    val pullRefreshState = rememberPullRefreshState(isRefreshing, { pagingItems.refresh() })

    Box(
        modifier = Modifier.pullRefresh(pullRefreshState)
    ) {
        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            checkRefreshLoadState(pagingItems)

            items(pagingItems.itemCount) { index ->
                pagingItems[index]?.let { recipe ->
                    RecipeItem(
                        recipe = recipe,
                        onDetailClick = { onRecipeClick.invoke(recipe.id) }
                    )
                }
            }
            checkAppendLoadState(pagingItems)
        }
    }
}

private fun LazyListScope.checkAppendLoadState(
    pagingItems: LazyPagingItems<Recipe>
) {
    if (pagingItems.loadState.append == LoadState.Loading) {
        item {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                CircularProgressIndicator(
                    Modifier.align(
                        Alignment.Center
                    )
                )
            }
        }
    }
}

private fun LazyListScope.checkRefreshLoadState(
    pagingItems: LazyPagingItems<Recipe>
) {
    when (pagingItems.loadState.refresh) {
        is LoadState.Loading -> loadingView()
        is LoadState.NotLoading -> doNothing()
        is LoadState.Error -> errorView()
        else -> doNothing()
    }
}

private fun doNothing() = Unit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeItem(
    recipe: Recipe,
    onDetailClick: () -> Unit
) {
    Card(
        onClick = onDetailClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.small),
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                modifier = Modifier.height(200.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(recipe.image)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(R.string.recipe_desc),
                contentScale = ContentScale.Crop
            )
            Spacer(
                modifier = Modifier.padding(top = MaterialTheme.spacing.verySmall)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = MaterialTheme.spacing.small,
                        vertical = MaterialTheme.spacing.verySmall
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = recipe.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Icon(
                    imageVector = Icons.Filled.FavoriteBorder,
                    contentDescription = stringResource(id = R.string.favorite)
                )
            }
            Row(
                modifier = Modifier.padding(
                    start = MaterialTheme.spacing.small,
                    bottom = MaterialTheme.spacing.small,
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.People,
                    contentDescription = stringResource(id = R.string.servings)
                )
                Text(
                    modifier = Modifier.padding(start = MaterialTheme.spacing.extraSmall),
                    text = stringResource(id = R.string.recipe_servings, recipe.servings),
                    style = MaterialTheme.typography.labelLarge
                )
                Icon(
                    imageVector = Icons.Filled.Circle,
                    contentDescription = stringResource(R.string.circle_divider),
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.spacing.extraSmall)
                        .scale(0.25f)
                )
                Icon(
                    imageVector = Icons.Filled.Timer,
                    contentDescription = stringResource(id = R.string.timer)
                )
                Text(
                    modifier = Modifier.padding(start = MaterialTheme.spacing.extraSmall),
                    text = stringResource(id = R.string.ready_in_minutes, recipe.readyInMinutes),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}

@Preview(
    uiMode = UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun RecipesScreenPreview() {
    val fakeRecipe = RecipeFactory.create()
    FoodlabTheme {
        Surface {
            Column {
                RecipeItem(recipe = fakeRecipe) {}
                RecipeItem(recipe = fakeRecipe) {}
                RecipeItem(recipe = fakeRecipe) {}
            }
        }
    }
}