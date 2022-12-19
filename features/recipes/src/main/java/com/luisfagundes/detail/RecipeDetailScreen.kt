package com.luisfagundes.detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@Composable
fun RecipeDetailScreen(
    id: Int = 0,
    navigator: DestinationsNavigator
) {
    Text(text = "Recipe Detail")
}