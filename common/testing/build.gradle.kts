plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.luisfagundes.testing"
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
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

dependencies {
    api("junit:junit:4.13.2")
    api("androidx.test.ext:junit:1.1.4")
    api("androidx.test.espresso:espresso-core:3.5.0")
    api("org.junit.jupiter:junit-jupiter")
    api("junit:junit:4.13.2")
    api("androidx.test.ext:junit:1.1.4")
    api("androidx.test.espresso:espresso-core:3.5.0")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.1")
    api("io.mockk:mockk:1.13.3")
    api("app.cash.turbine:turbine:0.7.0")
    api("com.google.truth:truth:1.1.3")
    implementation("androidx.core:core-ktx:+")
}