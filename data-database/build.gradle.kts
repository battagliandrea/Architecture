@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.dagger.hilt)
}
android {
    compileSdk = Configs.CompileSdk
    namespace = "com.ab21.data.database"

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
    implementation(projects.domain)

    implementation(libs.androidx.room)
    implementation(libs.androidx.room.ktx)
    implementation(libs.arrow.core)
    implementation(libs.javax.inject)
    implementation(libs.kotlin)
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.kotlin.serialization)
    implementation(libs.dagger.hilt.android)

    kapt(libs.androidx.room)
    kapt(libs.dagger.hilt.compiler)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.mockk.jvm)
    testImplementation(libs.kotlin.coroutines.test)

    testImplementation("io.kotest:kotest-assertions-core:5.5.4")
    testImplementation("io.kotest.extensions:kotest-assertions-arrow:1.3.0")
}
