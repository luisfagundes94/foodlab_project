package com.luisfagundes.home

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

enum class BottomBarHomeItem(
    @StringRes val title: Int,
    val icon: ImageVector
) {
    RECIPES(
        title = R.string.recipes,
        icon = Icons.Filled.Home
    ),
    SEARCH(
        title = R.string.search,
        icon = Icons.Filled.Search
    ),
    FAVORITES(
        title = R.string.favorites,
        icon = Icons.Filled.Favorite
    ),
    PANTRY(
        title = R.string.pantry,
        icon = Icons.Filled.ShoppingCart
    )
}