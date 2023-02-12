package com.luisfagundes.home

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.luisfagundes.provider.NavigationProvider
import com.luisfagundes.recipes.RecipesScreen
import com.luisfagundes.search.SearchScreen
import com.ramcosta.composedestinations.annotation.Destination

@OptIn(ExperimentalMaterial3Api::class)
@Destination(start = true)
@Composable
fun HomeScreen(
    navigator: NavigationProvider
) {
    val (currentBottomTab, setCurrentBottomTab) = rememberSaveable {
        mutableStateOf(BottomBarHomeItem.RECIPES)
    }

    Crossfade(currentBottomTab) { bottomTab ->
        Scaffold(
            bottomBar = {
                HomeBottomNavigation(
                    bottomTab = bottomTab,
                    setCurrentBottomTab = setCurrentBottomTab
                )
            }
        ) {
            val modifier = Modifier.padding(it)
            when (bottomTab) {
                BottomBarHomeItem.RECIPES -> RecipesScreen(
                    navigator = navigator,
                    modifier = modifier
                )
                BottomBarHomeItem.SEARCH -> SearchScreen()
                else -> {}
            }
        }
    }
}

@Composable
private fun HomeBottomNavigation(
    bottomTab: BottomBarHomeItem,
    setCurrentBottomTab: (BottomBarHomeItem) -> Unit
) {
    val pages = BottomBarHomeItem.values()

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
    ) {
        pages.forEach { page ->
            val selected = page == bottomTab
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = rememberVectorPainter(image = page.icon),
                        contentDescription = stringResource(page.title),
                    )
                },
                label = {
                    Text(
                        text = stringResource(page.title),
                        fontWeight = FontWeight.SemiBold,
                    )
                },
                selected = selected,
                onClick = {
                    setCurrentBottomTab.invoke(page)
                }
            )
        }
    }
}
