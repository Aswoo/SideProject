plugins {
    id("sdutest.android.library")
    id("sdutest.android.compose")
}

android {
    namespace = "com.sdutest.app2023.core.ui"
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.designsystem)
}
