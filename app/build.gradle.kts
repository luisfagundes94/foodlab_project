plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
}

@Suppress("UnstableApiUsage")
android {
    compileSdk = 33

    defaultConfig {
        applicationId = "com.luisfagundes.foodlab"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
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
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }

    packagingOptions {
        resources.pickFirsts.add("META-INF/LICENSE.md")
        resources.pickFirsts.add("META-INF/LICENSE-notice.md")
    }

    namespace = "com.luisfagundes.foodlab"
}

android.applicationVariants.all {
    kotlin.sourceSets {
        getByName(name) {
            kotlin.srcDir("build/generated/ksp/$name/kotlin")
        }
    }
}

dependencies {

    implementation(project(":features:home"))
    implementation(project(":features:splash"))
    implementation(project(":features:welcome"))
    implementation(project(":features:recipes"))
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":framework"))
    implementation(project(":extensions"))
    implementation(project(":common:theme"))
    implementation(project(":common:provider"))

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.compose.ui:ui:${Versions.compose}")
    implementation("androidx.compose.material3:material3:1.1.0-alpha03")
    implementation("androidx.compose.ui:ui-tooling-preview:${Versions.composeTooling}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation(Dependencies.UI.composeUi)

    implementation(Dependencies.DI.hiltAndroid)
    kapt(Dependencies.DI.hiltAndroidCompiler)
    implementation(Dependencies.DI.hiltNavigationCompose)

    implementation(Dependencies.Navigation.navigation)
    ksp(Dependencies.Navigation.destinationKsp)
    implementation(Dependencies.Navigation.destinationAnimation)
    implementation(Dependencies.Navigation.destinationCore)

    implementation(Dependencies.Accompanist.systemUiController)
}