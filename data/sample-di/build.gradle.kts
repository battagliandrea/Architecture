plugins {
    id("battman.android.library")
    id("battman.android.hilt")
}

android {
    namespace = "com.battman.data.sample.di"
}

dependencies {
    api(projects.data.sampleApi)
    api(projects.data.sampleImpl)
}
