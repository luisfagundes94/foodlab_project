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
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
}

dependencies {

    implementation(project(":common:theme"))
    implementation(project(":domain"))
    implementation(project(":framework"))

    implementation(Dependencies.UI.composeUi)
    implementation(Dependencies.UI.composeMaterial3)
    implementation(Dependencies.UI.composeRuntime)
    implementation(Dependencies.UI.composeTooling)
    implementation(Dependencies.UI.poolingContainer)
    implementation(Dependencies.UI.lottieCompose)
    implementation("androidx.core:core:1.9.0")
}