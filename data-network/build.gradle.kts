@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.dagger.hilt)
}
android {
    compileSdk = Configs.CompileSdk
    namespace = "com.ab21.data.network"

    defaultConfig {
        minSdk = Configs.MinSdk
        targetSdk = Configs.TargetSdk

        testInstrumentationRunner = Configs.AndroidJunitRunner
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

}

dependencies {

    implementation(projects.core)
    implementation(projects.data)
    implementation(projects.dataNetworkEnv)
    implementation(projects.domain)

    implementation(libs.arrow.core)
    implementation(libs.javax.inject)
    implementation(libs.kotlin)
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.kotlin.serialization)
    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.compiler)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit)
    implementation(libs.retrofit.serialization)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.mockk.jvm)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.okhttp.mock)

    testImplementation("io.kotest:kotest-assertions-core:5.5.4")
    testImplementation("io.kotest.extensions:kotest-assertions-arrow:1.3.0")
}
