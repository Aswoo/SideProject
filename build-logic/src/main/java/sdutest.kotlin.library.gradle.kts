import com.example.sdutest.configureKotest
import com.example.sdutest.configureKotlin

plugins {
    kotlin("jvm")
    id("sdutest.verify.detekt")
}

configureKotlin()
configureKotest()
