@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.kapt")
}

ksp {
    arg("compose-destinations.mode", "navgraphs")
    arg("compose-destinations.moduleName", "recipes")
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

    packagingOptions {
        resources.pickFirsts.add("META-INF/LICENSE.md")
        resources.pickFirsts.add("META-INF/LICENSE-notice.md")
    }

    namespace = "com.luisfagundes.recipes"
}

android.libraryVariants.all {
    kotlin.sourceSets {
        getByName(name) {
            kotlin.srcDir("build/generated/ksp/$name/kotlin")
        }
    }
}

dependencies {
    implementation(project(":common:provider"))
    implementation(project(":common:theme"))
    implementation(project(":framework"))
    implementation(project(":domain"))
    implementation(project(":extensions"))
    implementation(project(":common:component"))
    implementation(project(":common:testing"))

    implementation(Dependencies.UI.composeUi)
    implementation(Dependencies.UI.composeMaterial3)
    implementation(Dependencies.UI.composeMaterial)
    implementation(Dependencies.UI.composeTooling)
    implementation(Dependencies.UI.poolingContainer)
    implementation(Dependencies.UI.materialIconCore)
    implementation(Dependencies.UI.materialIconExtended)
    implementation(Dependencies.UI.composePaging)
    implementation(Dependencies.UI.coil)

    implementation(Dependencies.Accompanist.swipeToRefresh)

    implementation(Dependencies.Lifecycle.lifecycleRunTimeCompose)

    implementation(Dependencies.DI.hiltAndroid)
    implementation(Dependencies.DI.hiltNavigationCompose)
    testImplementation(project(mapOf("path" to ":domain")))
    kapt(Dependencies.DI.hiltAndroidCompiler)

    implementation(Dependencies.Navigation.navigation)
    implementation(Dependencies.Navigation.destinationCore)
    ksp(Dependencies.Navigation.destinationKsp)
    implementation(Dependencies.Navigation.destinationAnimation)
}