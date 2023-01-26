plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.luisfagundes.component"
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
        kotlinCompilerExtensionVersion = "1.4.0-alpha02"
    }
}

dependencies {

    implementation(project(":common:theme"))
    implementation(project(":domain"))
    implementation(project(":framework"))

    // Compose
    implementation(libs.compose.ui.ui)
    implementation(libs.compose.material3)
    implementation(libs.compose.ui.tooling)
    //implementation(Dependencies.UI.poolingContainer)
    //implementation(Dependencies.UI.lottieCompose)
    implementation("androidx.core:core:1.9.0")
    implementation("androidx.core:core-ktx:+")
}