plugins {
    alias(libs.plugins.weather.android.application)
    alias(libs.plugins.weather.android.application.compose)
    alias(libs.plugins.weather.hilt)
    alias(libs.plugins.kotlin.serialization)
//    alias(libs.plugins.kotlin.kapt)
//    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.example.weather"

    defaultConfig {
        applicationId = "com.example.weather"

        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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

    buildFeatures {
        compose = true
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

    // Default dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(projects.feature.home)
    implementation(projects.feature.search)

    // Accompanist
//    implementation(libs.accompanist.permissions)

    // Dagger Hilt
//    implementation(libs.hilt.android)
//    implementation(libs.androidx.hilt.navigation.compose)
//    kapt(libs.hilt.android.compiler)

    // Navigation Component
//    implementation(libs.androidx.navigation.compose)

    //------------------------------

    // Coil
//    implementation(libs.coil.compose)
//    implementation(libs.coil.network.okhttp)

    // Timber
    implementation(libs.timber)


    // Coroutines
//    implementation(libs.kotlinx.coroutines.android)


    // Lifecycle
    // ViewModel
//    implementation(libs.lifecycle.viewmodel.ktx)

    // Serialization
    implementation(libs.kotlinx.serialization.json)

}