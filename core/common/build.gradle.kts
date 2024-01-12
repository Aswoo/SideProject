plugins {
    id("sdutest.android.library")
    id("sdutest.android.hilt")
    id("kotlinx-serialization")
}

android {
    namespace = "com.example.sdutest.core.common"
}

dependencies {
    implementation(libs.coroutines.android)
    //testImplementation(projects.core.testing)
}
