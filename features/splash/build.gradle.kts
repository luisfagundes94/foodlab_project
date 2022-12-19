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
    kotlinOptions {
        jvmTarget = Versions.java
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
    namespace = "com.luisfagundes.splashscreen"
}

dependencies {

    // Libraries
    implementation(project(":common:theme"))
    implementation(project(":extensions"))
    implementation(project(":domain"))
    implementation(project(":framework"))

    // Core
    implementation(Dependencies.Core.composeActivity)

    // UI
    implementation(Dependencies.UI.splashScreen)
    implementation(Dependencies.UI.composeMaterial3)
    implementation(Dependencies.UI.composeUi)

    // DI
    implementation(Dependencies.DI.hiltAndroid)
    kapt(Dependencies.DI.hiltAndroidCompiler)
    implementation(Dependencies.DI.hiltNavigationCompose)
    implementation("androidx.core:core-ktx:1.9.0")
}