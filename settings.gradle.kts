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

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = ("foodlab")

include(
    ":app",
    ":features:splash",
    ":features:welcome",
    ":domain",
    ":common:theme",
    ":framework",
    ":features:home",
    ":common:provider",
    ":features:recipes",
    ":data",
    ":common:component",
    ":common:testing"
)

