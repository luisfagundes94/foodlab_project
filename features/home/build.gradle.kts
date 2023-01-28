plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
}

ksp {
    arg("compose-destinations.mode", "navgraphs")
    arg("compose-destinations.moduleName", "home")
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compilerextension.get()
    }
    namespace = "com.luisfagundes.home"
}

android.libraryVariants.all {
    kotlin.sourceSets {
        getByName(name) {
            kotlin.srcDir("build/generated/ksp/$name/kotlin")
        }
    }
}

dependencies {
    // Projects
    implementation(projects.common.provider)
    implementation(projects.common.theme)
    implementation(projects.features.recipes)
    implementation(projects.features.search)

    // Navigation
    implementation(libs.navigation.compose)
    ksp(libs.destination.ksp)
    implementation(libs.destination.animation)
    implementation(libs.destination.core)

    // Compose
    implementation(libs.compose.ui.ui)
    implementation(libs.compose.material3)
    implementation(libs.compose.ui.tooling)

    // Accompanist
    implementation(libs.accompanist.insets)
}