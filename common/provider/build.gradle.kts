plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.kapt")
    id("dagger.hilt.android.plugin")
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
        jvmTarget = "1.8"
    }
    namespace = "com.luisfagundes.provider"
}

dependencies {

//    implementation(Dependencies.Navigation.navigation)
//    implementation(Dependencies.Navigation.destinationCore)
//    implementation(Dependencies.Navigation.destinationAnimation)
//    implementation("androidx.core:core-ktx:+")
//    ksp(Dependencies.Navigation.destinationKsp)
//
//    implementation(Dependencies.UI.composeUi)
//    implementation(Dependencies.UI.composeMaterial3)
//
//    implementation(Dependencies.DI.hiltAndroid)
//    kapt(Dependencies.DI.hiltAndroidCompiler)
//    implementation(Dependencies.DI.hiltNavigationCompose)
}