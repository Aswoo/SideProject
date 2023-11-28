import com.example.sdutest.configureCoroutineAndroid
import com.example.sdutest.configureHiltAndroid
import com.example.sdutest.configureKotest
import com.example.sdutest.configureKotlinAndroid

plugins {
    id("com.android.library")
    id("sdutest.verify.detekt")
}

configureKotlinAndroid()
configureKotest()
configureCoroutineAndroid()
configureHiltAndroid()
