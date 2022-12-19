plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("dagger.hilt.android.plugin")
}
apply {
    plugin("kotlin-android")
}

android {
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
    namespace = "com.luisfagundes.welcome"
}

dependencies {
    implementation(project(":common:theme"))
    implementation(project(":extensions"))
    implementation(project(":framework"))
    implementation(project(":domain"))

    implementation(Dependencies.DI.hiltAndroid)
    kapt(Dependencies.DI.hiltAndroidCompiler)
    implementation(Dependencies.DI.hiltNavigationCompose)

    implementation(Dependencies.Navigation.navigation)
    implementation(Dependencies.Navigation.destinationCore)
    //ksp(Dependencies.Navigation.DestinationKsp)
    implementation(Dependencies.Navigation.destinationAnimation)

    implementation(Dependencies.Core.composeActivity)

    implementation(Dependencies.Accompanist.pager)
    implementation(Dependencies.Accompanist.indicators)
    implementation(Dependencies.Accompanist.systemUiController)

    implementation(Dependencies.UI.composeMaterial3)
    implementation(Dependencies.UI.composeUi)
    implementation(Dependencies.UI.composeTooling)
    implementation(Dependencies.UI.poolingContainer)
}