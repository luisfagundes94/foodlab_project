object Dependencies {

    object Core {
        const val lifecycleViewModelKtx =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleViewModel}"
        const val lifecycleViewModelCompose =
            "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycleViewModel}"
        const val composeActivity = "androidx.activity:activity-compose:${Versions.composeActivity}"
    }

    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val gson = "com.google.code.gson:gson:${Versions.gson}"
        const val okHttp3 = "com.squareup.okhttp3:okhttp:5.0.0-alpha.5"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    }

    object Storage {
        const val roomKtx = "androidx.room:room-ktx:2.4.2"
        const val roomCompiler = "androidx.room:room-compiler:2.4.2"
        const val dataStorePref = "androidx.datastore:datastore-preferences:1.0.0"
        const val dataStore = "androidx.datastore:datastore:1.0.0"
        const val securityPref = "androidx.security:security-crypto-ktx:1.1.0-alpha03"
    }

    object DI {
        const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val hiltAndroidTesting = "com.google.dagger:hilt-android-testing:${Versions.hilt}"
        const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
        const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:1.0.0"
    }

    object Accompanist {
        const val swipeToRefresh = "com.google.accompanist:accompanist-swiperefresh:0.23.1"
        const val systemUiController = "com.google.accompanist:accompanist-systemuicontroller:0.23.1"
        const val insets = "com.google.accompanist:accompanist-insets:0.23.1"
        const val placeholderMaterial = "com.google.accompanist:accompanist-placeholder-material:0.23.1"
        const val navigationMaterial = "com.google.accompanist:accompanist-navigation-material:0.23.1"
        const val permissions = "com.google.accompanist:accompanist-permissions:0.23.1"
        const val pager = "com.google.accompanist:accompanist-pager:0.23.1"
        const val indicators = "com.google.accompanist:accompanist-pager-indicators:0.23.1"
        const val webview = "com.google.accompanist:accompanist-webview:0.24.4-alpha"
    }

    object Logging {
        const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    }

    object Navigation {
        const val navigation = "androidx.navigation:navigation-compose:2.4.2"
        const val destinationCore = "io.github.raamcosta.compose-destinations:core:1.7.27-beta"
        const val destinationKsp = "io.github.raamcosta.compose-destinations:ksp:1.7.27-beta"
        const val destinationAnimation = "io.github.raamcosta.compose-destinations:animations-core:1.7.27-beta"
    }

    object Lifecycle {
        const val lifecycleRunTimeCompose = "androidx.lifecycle:lifecycle-runtime-compose:${Versions.lifecycle}"
        const val lifecycleViewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycle}"
        const val lifecycleCompose = "androidx.lifecycle:lifecycle-runtime-compose:${Versions.lifecycle}"
    }

    object UI {
        const val lottie = "com.airbnb.android:lottie:${Versions.lottie}"
        const val lottieCompose = "com.airbnb.android:lottie-compose:${Versions.lottie}"
        const val paging = "androidx.paging:paging-runtime:${Versions.paging}"
        const val composePaging = "androidx.paging:paging-compose:${Versions.pagingCompose}"
        const val composeUi = "androidx.compose.ui:ui:${Versions.composeUi}"
        const val composeMaterial = "androidx.compose.material:material:${Versions.composeUi}"
        const val composeMaterial3 = "androidx.compose.material3:material3:${Versions.composeMaterial3}"
        const val materialIconCore = "androidx.compose.material:material-icons-core:1.3.1"
        const val materialIconExtended = "androidx.compose.material:material-icons-extended:1.3.1"
        const val coil = "io.coil-kt:coil-compose:2.2.2"

        const val material = "com.google.android.material:material:${Versions.material}"
        const val splashScreen = "androidx.core:core-splashscreen:${Versions.splashScreen}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val composeTooling = "androidx.compose.ui:ui-tooling:${Versions.composeTooling}"
        const val composeRuntime = "androidx.compose.runtime:runtime:1.1.1"
        const val poolingContainer = "androidx.customview:customview-poolingcontainer:1.0.0"
    }

    object Testing {
        const val truth = "com.google.truth:truth:1.1.3"
        const val mockk = "io.mockk:mockk:${Versions.mockk}"
        const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4"
        const val junit5 = "org.junit.jupiter:junit-jupiter"
    }
}