import com.battman.gradle.plugins.extensions.readProperties

plugins {
    id("battman.android.application")
    id("battman.android.hilt")
}

android {
    namespace = "com.battman.cleanarchitecture"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.battman.cleanarchitecture"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core)
    implementation(libs.androidx.core.splashscreen)

    implementation(projects.common.theme)
    implementation(projects.core.dispatcher.dispatcherDi)
    implementation(projects.core.network.networkDi)
    implementation(projects.core.ui.navigation)
    implementation(projects.data.sampleDi)
    implementation(projects.features.one.presentation)
    implementation(projects.features.two.presentation)
}
