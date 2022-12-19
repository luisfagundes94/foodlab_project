plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

ksp {
    arg("compose-destinations.mode", "navgraphs")
    arg("compose-destinations.moduleName", "home")
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

    implementation(project(":common:provider"))
    implementation(project(":common:theme"))
    implementation(project(":extensions"))
    implementation(project(":features:recipes"))

    implementation(Dependencies.Navigation.navigation)
    ksp(Dependencies.Navigation.destinationKsp)
    implementation(Dependencies.Navigation.destinationAnimation)
    implementation(Dependencies.Navigation.destinationCore)

    implementation(Dependencies.UI.composeUi)
    implementation(Dependencies.UI.composeMaterial3)
    implementation(Dependencies.UI.composeMaterial)
    implementation(Dependencies.UI.composeTooling)
    implementation(Dependencies.UI.poolingContainer)

    implementation(Dependencies.Accompanist.insets)
}