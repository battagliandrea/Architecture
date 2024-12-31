import com.battman.gradle.plugins.extensions.readProperties

plugins {
    id("battman.android.library")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.battman.sample.feature.one.data.impl"
}

dependencies {
    api(projects.features.one.data.dataApi)

    implementation(libs.javax.inject)
}
