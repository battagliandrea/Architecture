pluginManagement {
    includeBuild("gradle/plugins")
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
    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "dagger.hilt.android.plugin" -> useModule("com.google.dagger:hilt-android-gradle-plugin:${requested.version}")
            }
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "CleanArchitecture"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(
    "app",
    ":common:theme",
    ":core:dispatcher:dispatcher-api",
    ":core:dispatcher:dispatcher-di",
    ":core:network:network-api",
    ":core:network:network-di",
    ":core:network:network-test",
    ":core:test:test-jvm",
    ":core:ui:mvi",
    ":core:ui:navigation",
    ":core:usecase:usecase-api",
    ":data:sample-api",
    ":data:sample-di",
    ":data:sample-impl",
    ":domain:models",
    ":domain:usecases",
    ":features:one:presentation",
    ":features:two:presentation",
)
