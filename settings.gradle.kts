pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "hydration-tracker"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":wear-app")

include(":core:designsystem:android")
include(":core:designsystem:common")
include(":core:designsystem:glance")
include(":core:designsystem:wear")
include(":core:common")
include(":core:domain")
include(":core:model")
include(":core:database")
include(":core:data")
include(":core:ui")
include(":core:network")

include(":feature:hydration:wear:summary")

include(":lint")