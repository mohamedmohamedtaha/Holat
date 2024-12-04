plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.dev.tools.ksp)
    alias(libs.plugins.safe.args)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.holat.login"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
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
    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.compiler.ksp)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)
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
    kapt(libs.glide.compiler)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.smart.mateial.spinner)
    implementation(libs.android.volley)

    // Import the BoM for Firebase
    implementation(platform(libs.firebase.bom)) // Import the Firebase BoM

    // Declare the dependencies for the desired Firebase products
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.messaging.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}