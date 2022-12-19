package com.luisfagundes.provider

import androidx.navigation.NavController
import com.luisfagundes.detail.destinations.RecipeDetailScreenDestination
import com.ramcosta.composedestinations.navigation.navigate

class AppNavigationProvider(
    private val navController: NavController
) : NavigationProvider {
    override fun openRecipeDetail(recipeId: Int) {
        navController.navigate(RecipeDetailScreenDestination(recipeId))
    }
}