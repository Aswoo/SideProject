plugins {
    id("sdutest.android.feature")
}

android {
    namespace = "com.example.sdutest.feature.bookmarks"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.kotlinx.immutable)
}
