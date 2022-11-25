@Suppress(
    "DSL_SCOPE_VIOLATION",
    "UnstableApiUsage"
)
plugins {
    `version-catalog`
    alias(libs.plugins.kotlin.jvm)  apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.kotlin.android)  apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.dagger.hilt) apply false
    alias(libs.plugins.androidx.navigation.safeargs) apply false
}

allprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_11.toString()
            freeCompilerArgs = Configs.FreeCoroutineCompilerArgs
            languageVersion = JavaVersion.VERSION_1_8.toString()
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}