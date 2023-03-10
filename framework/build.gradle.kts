plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0"
    }
    namespace = "com.luisfagundes.framework"
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("com.google.dagger:hilt-android:2.44.2")
    kapt("com.google.dagger:hilt-android-compiler:2.44.2")

    implementation("com.jakewharton.timber:timber:5.0.1")
    implementation("androidx.paging:paging-runtime-ktx:3.1.1")

    implementation("com.google.accompanist:accompanist-insets:0.28.0")

    implementation("androidx.compose.ui:ui:1.3.3")
    implementation("androidx.compose.material3:material3:1.0.1")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
    implementation("com.airbnb.android:lottie-compose:5.2.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.3.3")
    implementation("androidx.compose.runtime:runtime:1.3.3")
    implementation("androidx.compose.foundation:foundation:1.3.1")
    implementation("androidx.palette:palette-ktx:1.0.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation("androidx.core:core-ktx:1.9.0")

    implementation("androidx.security:security-crypto-ktx:1.1.0-alpha04")
}