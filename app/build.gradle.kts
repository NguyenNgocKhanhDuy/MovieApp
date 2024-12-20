//plugins {
//    alias(libs.plugins.androidApplication)
//    alias(libs.plugins.googleGmsGoogleServices)
//}
// build.gradle.kts (App level)
plugins {
//    alias(libs.plugins.androidApplication)
//    alias(libs.plugins.googleGmsGoogleServices)
    id("com.android.application")
    id("com.google.gms.google-services")
    alias(libs.plugins.jetbrainsKotlinAndroid)
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
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        viewBinding = true
        compose = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation ("de.hdodenhof:circleimageview:3.1.0")
    implementation ("androidx.recyclerview:recyclerview:1.3.2")

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.media3.datasource)
    implementation(libs.firebase.auth)

    implementation(libs.firebase.database)

    implementation(libs.navigation.fragment)
//    implementation(libs.recyclerview)
    implementation(libs.navigation.ui)
    implementation(libs.firebase.inappmessaging)
    implementation(libs.media3.exoplayer)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation("org.jetbrains:annotations:15.0")

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation ("com.google.code.gson:gson:2.11.0")
    implementation ("com.squareup.retrofit2:retrofit:2.11.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.11.0")


    // google navigationbar
    implementation ("com.google.android.material:material:1.12.0")

    implementation ("com.google.android.exoplayer:exoplayer:2.16.1")
    implementation ("com.google.android.exoplayer:exoplayer-hls:2.16.1")

    implementation(platform("com.google.firebase:firebase-bom:32.8.0"))



    implementation("androidx.appcompat:appcompat:1.4.0")
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

    implementation("com.google.android.gms:play-services-auth:21.0.0")
    implementation("com.google.firebase:firebase-database")

    implementation("com.android.support:multidex:1.0.3")
    implementation("androidx.viewpager2:viewpager2:1.0.0")
    

    implementation("androidx.recyclerview:recyclerview")
    implementation("com.google.android.flexbox:flexbox:3.0.0")

    implementation ("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.16.0")
    implementation ("de.hdodenhof:circleimageview:3.1.0")
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
    implementation ("com.pierfrancescosoffritti.androidyoutubeplayer:core:12.1.0")
    implementation("com.google.firebase:firebase-auth:21.0.8")
    implementation("com.google.firebase:firebase-storage:20.1.0")
    implementation("com.github.bumptech.glide:glide:4.13.2")
    annotationProcessor("com.github.bumptech.glide:compiler:4.13.2")
}
