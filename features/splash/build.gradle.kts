plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt)
}

android {
    compileSdk = libs.versions.compile.sdk.version.get().toInt()

    defaultConfig {
        minSdk = libs.versions.min.sdk.version.get().toInt()
        targetSdk = libs.versions.target.sdk.version.get().toInt()

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compilerextension.get()
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
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.splashscreen)

    // UI
    implementation(libs.compose.material3)
    implementation(libs.compose.ui.ui)

    // Dependency Injection
    implementation(libs.hilt.library)
    implementation(libs.hilt.compose)
    kapt(libs.hilt.compiler)
}