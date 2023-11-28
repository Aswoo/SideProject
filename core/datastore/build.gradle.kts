plugins {
    id("sdutest.android.library")
}

android {
    namespace = "com.example.sdutest.core.datastore"
}

dependencies {
    testImplementation(libs.junit4)
    testImplementation(libs.kotlin.test)
    implementation(libs.androidx.datastore)
}
