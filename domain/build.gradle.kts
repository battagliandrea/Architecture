@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    `java-library`
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    implementation(projects.core)

    implementation(libs.arrow.core)
    implementation(libs.javax.inject)
    implementation(libs.kotlin)
    implementation(libs.kotlin.serialization)
    implementation(libs.kotlin.coroutines.core)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.mockk.jvm)
    testImplementation(libs.kotlin.coroutines.test)
}
