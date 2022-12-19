package com.luisfagundes.onboarding

import androidx.annotation.DrawableRes
import com.luisfagundes.theme.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {
    object First : OnBoardingPage(
        image = R.drawable.recipe,
        title = "Recipes",
        description = "You can browse through over 300,000 recipes."
    )

    object Second : OnBoardingPage(
        image = R.drawable.ingredient,
        title = "Ingredients",
        description = "You can search for recipes using your current home ingredients."
    )

    object Third : OnBoardingPage(
        image = R.drawable.share,
        title = "Share",
        description = "You can share recipes as PDF's or Images to your friends and family."
    )
}