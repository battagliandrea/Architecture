plugins {
    id("battman.jvm.library")
}

dependencies {
    api(libs.arrow.core)
    api(libs.kotlinx.coroutines.core.jvm)
}