plugins {
    id("battman.android.library")
    id("battman.android.hilt")
}

android {
    namespace = "com.battman.sample.feature.one.data.di"
}

dependencies {
    api(projects.features.one.data.dataApi)
    api(projects.features.one.data.dataImpl)
}
