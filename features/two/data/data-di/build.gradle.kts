plugins {
    id("battman.android.library")
    id("battman.android.hilt")
}

android {
    namespace = "com.battman.sample.feature.two.data.di"
}

dependencies {
    api(projects.features.two.data.dataApi)
    api(projects.features.two.data.dataImpl)
}
