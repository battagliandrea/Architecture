plugins {
    id("battman.android.library")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.battman.core.network.test"
}

dependencies {
    api(projects.core.network.networkApi)

    api(libs.junit)
    api(libs.kotlinx.coroutines.test)
    api(libs.okhttp.mockwebserver)
}
