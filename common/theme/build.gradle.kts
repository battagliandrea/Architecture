plugins {
    id("battman.android.library.compose")
}

android {
    namespace = "com.battman.sample.common.theme"
}

dependencies {
    api(libs.bundles.compose)
}
