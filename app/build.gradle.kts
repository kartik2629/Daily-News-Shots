plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    kotlin("kapt")
}

android {
    namespace = "com.example.dailynewsshots"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.dailynewsshots"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
    }
}


dependencies {

    // Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Material 3
    implementation(libs.material)

    // Lifecycle (ViewModel + LiveData)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.livedata)

    // RecyclerView
    implementation(libs.androidx.recyclerview)

    // Swipe Refresh
    implementation(libs.androidx.swiperefreshlayout)

    // Retrofit + OkHttp + GSON
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp.logging)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // Room (Bookmark feature)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)

    // Glide
    implementation(libs.glide)
    kapt(libs.glide.compiler)

    // Timber
    implementation(libs.timber)

    // Shimmer
    implementation(libs.shimmer)

    // Paging 3 (recommended for news apps)
    implementation(libs.paging.runtime)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
