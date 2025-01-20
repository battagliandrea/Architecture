import com.battman.gradle.plugins.extensions.readProperties

plugins {
    id("battman.android.library")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.battman.data.sample.impl"
}

dependencies {
    api(projects.data.sampleApi)

    implementation(libs.javax.inject)
}
