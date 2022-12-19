package com.luisfagundes.splashscreen.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.luisfagundes.splashscreen.presentation.StartViewModel
import com.luisfagundes.extensions.launchActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class StartActivity : ComponentActivity() {

    private val viewModel: StartViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        setUpSplashScreenApi()
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenCreated {
            viewModel.startWelcome.collectLatest {
                delay(3000)
                if (it) navigateToWelcomeActivity() else navigateToMainActivity()
            }
        }
    }

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S, lambda = 0)
    private fun setUpSplashScreenApi() {
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { true }
    }

    private fun navigateToWelcomeActivity() {
        launchActivity(
            packageName = packageName,
            className = "com.luisfagundes.welcome.WelcomeActivity"
        ).also {
            finish()
        }
    }

    private fun navigateToMainActivity() {
        launchActivity(
            packageName = packageName,
            className = "com.luisfagundes.navigation.NavigationActivity"
        ).also {
            finish()
        }
    }
}

