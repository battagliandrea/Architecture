import com.battman.gradle.plugins.extensions.readProperties

plugins {
    id("battman.android.library")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.battman.sample.feature.two.data.impl"
}

dependencies {
    api(projects.features.two.data.dataApi)

    implementation(libs.javax.inject)
}
