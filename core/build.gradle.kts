@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    `java-library`
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(libs.arrow.core)
    implementation(libs.javax.inject)
    implementation(libs.kotlin)
    implementation(libs.kotlin.coroutines.core)
}
