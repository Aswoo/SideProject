plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.verify.detektPlugin)
}

gradlePlugin {
    plugins {
        register("androidHilt") {
            id = "sdutest.android.hilt"
            implementationClass = "com.example.sdutest.HiltAndroidPlugin"
        }
        register("kotlinHilt") {
            id = "sdutest.kotlin.hilt"
            implementationClass = "com.example.sdutest.HiltKotlinPlugin"
        }
    }
}
