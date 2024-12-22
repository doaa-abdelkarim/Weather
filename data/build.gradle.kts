plugins {
    alias(libs.plugins.weather.android.library)
//    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.weather.hilt)

}

android {
    namespace = "com.example.data"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(projects.core.common)
    implementation(projects.domain)

    // DataStore
    implementation(libs.androidx.datastore.preferences)

    // Gson
    implementation(libs.converter.gson)

    // Okhttp logging interceptor
    implementation(libs.logging.interceptor)

    // Retrofit
    implementation(libs.retrofit)

    //Timber
    implementation(libs.timber)
}