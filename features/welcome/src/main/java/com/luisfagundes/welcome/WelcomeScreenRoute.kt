package com.luisfagundes.welcome

sealed class WelcomeScreenRoute(val value: String) {
    object OnBoarding : WelcomeScreenRoute(value = "onboarding_screen")
    object Environment : WelcomeScreenRoute(value = "environment_screen")
}