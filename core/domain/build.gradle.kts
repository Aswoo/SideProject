plugins {
    id("sdutest.android.library")
}

android {
    namespace = "com.example.sdutest.core.domain"
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.model)

    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.inject)
}
