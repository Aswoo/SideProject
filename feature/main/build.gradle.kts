plugins {
    id("sdutest.android.feature")
}

android {
    namespace = "com.example.sdutest.feature.main"
}

dependencies {
//    implementation(projects.feature.home)
//    implementation(projects.feature.setting)
//    implementation(projects.feature.contributor)
//    implementation(projects.feature.session)
//    implementation(projects.feature.bookmark)
//
//    implementation(projects.widget)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.kotlinx.immutable)
}
