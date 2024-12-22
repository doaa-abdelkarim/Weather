pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "Weather"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
//include(":build-logic:convention")
include(":core:common")
include(":core:designsystem")
include(":core:localization")
include(":data")
include(":domain")
include(":feature:home")
include(":feature:search")