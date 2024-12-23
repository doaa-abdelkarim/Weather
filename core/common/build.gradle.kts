plugins {
    alias(libs.plugins.weather.android.library)
    alias(libs.plugins.weather.android.library.compose)
}

android {
    namespace = "com.example.common"

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

    //Play Services Location
    implementation (libs.play.services.location)

    //Material3 Window Size
    implementation(libs.androidx.compose.material3.windowSizeClass)


}