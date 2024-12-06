plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.dev.tools.ksp)
    alias(libs.plugins.safe.args)
}

android {
    namespace = "com.holat.holat"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.holat.holat"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.rerofit2)
    implementation(libs.retrofit2.converter.gson)
    implementation(libs.retrofit2.adapter)
    implementation(libs.okhttp3.logging.intercepter)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.compiler.ksp)
    implementation(libs.datastore.preferences)
    implementation(libs.coil.kt)
    implementation(libs.font.dp)
    implementation(libs.font.sp)
    implementation(libs.phone.number.picker)
    implementation(libs.android.image.cropper)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.country.code.picker)
    implementation(libs.glide)
    implementation(libs.paging.runtime)
    kapt(libs.glide.compiler)
    implementation(libs.github.lingver)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.smart.mateial.spinner)
    implementation(libs.android.volley)

    implementation(project(":login"))

}