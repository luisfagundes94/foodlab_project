pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        includeBuild("plugins")
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = ("foodlab")

//enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(
    ":app",
    ":features:splash",
    ":features:welcome",
    ":domain",
    ":common:theme",
    ":extensions",
    ":framework",
    ":features:home",
    ":common:provider",
    ":features:recipes",
    ":data",
    ":common:component",
    ":common:testing"
)

