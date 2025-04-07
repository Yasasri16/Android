plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.googleGmsGoogleServices) apply false

}
android {
    namespace = "com.example.truthordare"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.truthordare"
        minSdk = 23
        targetSdk = 35
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
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.auth)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(libs.picasso)
    implementation(libs.retrofit)
    implementation(libs.retrofitConverterGson)
    implementation(libs.gson)
    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.core)
    implementation(platform(libs.firebase.bom))
    implementation("com.google.firebase:firebase-firestore")

}
