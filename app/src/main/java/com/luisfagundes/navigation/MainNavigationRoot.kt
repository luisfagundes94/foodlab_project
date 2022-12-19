package com.luisfagundes.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.luisfagundes.provider.AppNavigationProvider
import com.luisfagundes.provider.shouldUseDarkMode
import com.luisfagundes.theme.FoodlabTheme
import com.luisfagundes.welcome.SetupSystemUi
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency

@Composable
fun MainNavigationRoot(
    finish: () -> Unit
) {

    val navController = rememberNavController()
    val currentBackStackEntryAsState by navController.currentBackStackEntryAsState()
    val destination = currentBackStackEntryAsState?.destination?.route
        ?: RootNavGraph

    if (destination == RootNavGraph.startRoute.startRoute.route) {
        BackHandler { finish() }
    }

    FoodlabTheme {
        SetupSystemUi(rememberSystemUiController(), MaterialTheme.colorScheme.surface)

        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            DestinationsNavHost(
                navController = navController,
                navGraph = RootNavGraph,
                dependenciesContainerBuilder = {
                    dependency(AppNavigationProvider(navController))
                }
            )
        }
    }
}