plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

android {
    compileSdk = libs.versions.compile.sdk.version.get().toInt()

    defaultConfig {
        applicationId = "com.luisfagundes.foodlab"
        minSdk = libs.versions.min.sdk.version.get().toInt()
        targetSdk = libs.versions.target.sdk.version.get().toInt()
        versionCode = 1
        versionName = "0.1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            versionNameSuffix = "-debug"
        }
    }

    sourceSets {
        getByName("debug") {
            java.srcDir("src/debugRelease/java")
        }
        getByName("release") {
            java.srcDir("src/debugRelease/java")
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

    // Core
    implementation(libs.androidx.core.ktx)

    // Compose
    implementation(libs.compose.ui.ui)
    implementation(libs.compose.material3)
    implementation(libs.compose.ui.tooling)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.compose.material.material)

    // Lifecycle
    implementation(libs.lifecycle.runtime.ktx)

    // Dependency Injection
    implementation(libs.hilt.library)
    implementation(libs.hilt.compose)
    kapt(libs.hilt.compiler)

    // Navigation
    implementation(libs.navigation.compose)
    ksp(libs.destination.ksp)
    implementation(libs.destination.animation)
    implementation(libs.destination.core)

    // Accompanist (Compose Useful Libraries)
    implementation(libs.accompanist.systemuicontroller)
}
repositories {
    google()
    mavenCentral()
}
