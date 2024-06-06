buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.2")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
//plugins {
//    alias(libs.plugins.androidApplication) apply false
//    alias(libs.plugins.googleGmsGoogleServices) apply false
//}
// build.gradle.kts (Project level)
plugins {
    id("com.android.application") version "8.2.2" apply false
    id("com.android.library") version "8.2.2" apply false
    id("com.google.gms.google-services") version "4.3.14" apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
}

allprojects {

}
