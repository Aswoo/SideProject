plugins {
    id("sdutest.android.library")
}

android {
    namespace = "com.example.sdutest.core.testing"
}

dependencies {
    api(libs.junit4)
    api(libs.junit.vintage.engine)
    api(libs.kotlin.test)
    api(libs.mockk)
    api(libs.turbine)
    api(libs.coroutines.test)
}
