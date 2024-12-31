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
    ":domain:models",
    ":features:one:data:data-api",
    ":features:one:data:data-di",
    ":features:one:data:data-impl",
    ":features:one:domain:usecases",
    ":features:one:presentation",
    ":features:two:data:data-api",
    ":features:two:data:data-di",
    ":features:two:data:data-impl",
    ":features:two:domain:usecases",
    ":features:two:presentation",
)
