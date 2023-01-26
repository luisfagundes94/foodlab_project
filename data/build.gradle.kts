import org.jetbrains.kotlin.konan.properties.Properties
import java.io.FileInputStream

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("dagger.hilt.android.plugin")
}

val apiKeyPropertiesFile = rootProject.file("apikey.properties")
val apiKeyProperties = Properties()
apiKeyProperties.load(FileInputStream(apiKeyPropertiesFile))

android {
    namespace = "com.luisfagundes.data"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "API_KEY", "\"${getApiKey()}\"")
        buildConfigField("String", "BASE_URL", "\"https://api.spoonacular.com/\"")
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
}

dependencies {

//    implementation(project(":domain"))
//    implementation(project(":extensions"))
//    implementation(project(":common:testing"))
//
//    implementation(Dependencies.DI.hiltAndroid)
//    implementation("androidx.core:core-ktx:+")
//    kapt(Dependencies.DI.hiltAndroidCompiler)
//
//    implementation(Dependencies.Storage.dataStore)
//    implementation(Dependencies.Storage.dataStorePref)
//
//    implementation(Dependencies.Network.retrofit)
//    implementation(Dependencies.Network.retrofitGson)
//    implementation(Dependencies.Network.okHttp3)
//    implementation(Dependencies.Network.loggingInterceptor)
//
//    implementation(Dependencies.UI.composePaging)
}

fun getApiKey() = apiKeyProperties["API_KEY"]