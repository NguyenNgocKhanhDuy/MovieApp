//plugins {
//    alias(libs.plugins.androidApplication)
//    alias(libs.plugins.googleGmsGoogleServices)
//}
// build.gradle.kts (App level)
plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}
android {
    namespace = "com.example.movieapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.movieapp"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.media3.datasource)
    implementation(libs.firebase.auth)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation ("com.google.code.gson:gson:2.11.0")
    implementation ("com.squareup.retrofit2:retrofit:2.11.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.11.0")


    // google navigationbar
    implementation ("com.google.android.material:material:1.4.0")

    implementation ("com.google.android.exoplayer:exoplayer:2.16.1")
    implementation ("com.google.android.exoplayer:exoplayer-hls:2.16.1")



    dependencies {
    implementation("androidx.appcompat:appcompat:1.4.0")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.activity:activity:1.2.3")
    implementation("androidx.constraintlayout:constraintlayout:2.1.0")
    implementation("androidx.media3:media3-datasource:1.0.0")

    implementation("com.google.firebase:firebase-auth:23.0.0")
    implementation("com.google.code.gson:gson:2.11.0")
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.google.android.exoplayer:exoplayer:2.16.1")
    implementation("com.google.android.exoplayer:exoplayer-hls:2.16.1")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}






}