plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.safeArgs)
    alias(libs.plugins.serialization)
    kotlin("kapt")
}

android {
    namespace = "com.otkritie.hackaton"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.otkritie.hackaton"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner += "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
        isCoreLibraryDesugaringEnabled = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(libs.core)
    implementation(libs.appcompat)
    implementation(libs.constraintLayout)
    implementation(libs.material)
    implementation(libs.lifecycle.core)
    implementation(libs.lifecycle.runtime)
    implementation(libs.lifecycle.livedata)

    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)
    implementation(libs.hilt.android)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    kapt(libs.hilt.compiler)

    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)

    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp.core)
    implementation(libs.okhttp.log)
    implementation(libs.retrofit)
    implementation(libs.serialization)
    implementation(libs.converterFactory)

    implementation(libs.circleImageView)
    coreLibraryDesugaring(libs.desugarJdkLibs)
}
