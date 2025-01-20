plugins {
    id("battman.jvm.library")
}

dependencies {
    api(projects.core.usecase.usecaseApi)

    implementation(libs.javax.inject)
}
