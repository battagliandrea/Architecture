plugins {
    id("battman.android.library.compose")
}

android {
    namespace = "com.battman.core.mvi"
}

dependencies {
    api(libs.bundles.compose.lifecycle)
}
