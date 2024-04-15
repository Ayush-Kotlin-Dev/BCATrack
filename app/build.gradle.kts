plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
    id("com.google.dagger.hilt.android")
    id ("kotlinx-serialization")
}

android {
    namespace = "ggv.ayush.bcatrack"
    compileSdk = 34

    defaultConfig {
        applicationId = "ggv.ayush.bcatrack"
        minSdk = 28
        targetSdk = 34
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.compose.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    // Horizontal Pager and Indicators - Accompanist
    implementation("com.google.accompanist:accompanist-pager: 0.35.0-alpha")
    implementation ("com.google.accompanist:accompanist-pager-indicators:0.35.0-alpha")

    implementation ("org.apache.poi:poi-ooxml:5.2.4")
        implementation ("androidx.compose.runtime:runtime:1.6.5")

    implementation("com.google.dagger:hilt-android:2.51")
    ksp("com.google.dagger:hilt-compiler:2.51")

    // For instrumentation tests
    androidTestImplementation ("com.google.dagger:hilt-android-testing:2.51")
    kspAndroidTest("com.google.dagger:hilt-compiler:2.51")

    // For local unit tests
    testImplementation("com.google.dagger:hilt-android-testing:2.51")
    kspTest("com.google.dagger:hilt-compiler:2.51")


    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

    //Navigation
    val nav_version = "2.7.7"
    val compose_version = "1.6.3"
    implementation("androidx.navigation:navigation-compose:$nav_version")
    implementation("androidx.compose.ui:ui:$compose_version")
    implementation("androidx.compose.material:material:$compose_version")
    implementation("androidx.compose.ui:ui-tooling-preview:$compose_version")

    //hilt
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    implementation("io.coil-kt:coil-compose:2.6.0")
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.5.0")

    val room = "2.6.1"
    // Room
    implementation("androidx.room:room-runtime:$room")
    implementation("androidx.room:room-ktx:$room")
    ksp("androidx.room:room-compiler:$room")
    implementation("androidx.room:room-paging:2.6.1")

    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")

    // Paging 3.0
    implementation("androidx.paging:paging-compose:1.0.0-alpha14")

    // KotlinX Serialization
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2")

    // DataStore Preferences
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Coil
    implementation("io.coil-kt:coil-compose:2.6.0")

    // System UI Controller - Accompanist
    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.35.0-alpha")
    // Swipe to Refresh - Accompanist
    implementation ("com.google.accompanist:accompanist-swiperefresh:0.35.0-alpha")






}