package com.luisfagundes.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.luisfagundes.onboarding.OnBoardingScreen
import com.luisfagundes.welcome.WelcomeScreenRoute

@Composable
fun WelcomeNavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = WelcomeScreenRoute.OnBoarding.value
    ) {
        composable(route = WelcomeScreenRoute.OnBoarding.value) {
            OnBoardingScreen()
        }
    }
}