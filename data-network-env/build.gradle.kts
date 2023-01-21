@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.dagger.hilt)
}
android {
    compileSdk = Configs.CompileSdk
    namespace = "com.ab21.data.network.env"

    defaultConfig {
        minSdk = Configs.MinSdk
        targetSdk = Configs.TargetSdk

        buildConfigField("String", "FLAVOR", "\"production\"")
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

    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.compiler)
}
