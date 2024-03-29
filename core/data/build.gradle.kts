plugins {
    id("sdutest.android.library")
    id("sdutest.android.hilt")
    id("kotlinx-serialization")
}

android {
    namespace = "com.example.sdutest.core.data"
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.datastore)
    implementation(projects.core.common)

    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)

    implementation(libs.androidx.core.ktx)
    implementation(libs.coroutines.android)
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.datetime)
    testImplementation(libs.turbine)
}
