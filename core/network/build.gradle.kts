plugins {
    id("sdutest.android.library")
    id("sdutest.android.hilt")
    id("kotlinx-serialization")
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "com.example.sdutest.core.network"
}

dependencies {
//    implementation(projects.core.model)
//    implementation(projects.core.datastore)

    implementation(libs.androidx.core.ktx)
    implementation(libs.coroutines.android)
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.datetime)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)

    implementation(libs.coil.kt)
    implementation(libs.coil.kt.svg)

    testImplementation(libs.turbine)
}
