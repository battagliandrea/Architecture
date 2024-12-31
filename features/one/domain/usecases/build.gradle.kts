plugins {
    id("battman.jvm.library")
}

dependencies {
    api(projects.core.usecase.usecaseApi)
    api(projects.domain.models)

    implementation(libs.javax.inject)
}
