plugins {
    id("sdutest.android.feature")
}

android {
    namespace = "com.example.sdutest.feature.setting"
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.oss.licenses)
}
