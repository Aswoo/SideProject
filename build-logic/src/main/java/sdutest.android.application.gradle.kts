import com.example.sdutest.configureHiltAndroid
import com.example.sdutest.configureKotestAndroid
import com.example.sdutest.configureKotlinAndroid

plugins {
    id("com.android.application")
}

configureKotlinAndroid()
configureHiltAndroid()
configureKotestAndroid()
