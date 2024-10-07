plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.example.eventeasy"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.eventeasy"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        // Aquí se define el campo API_BASE_URL
        //buildConfigField("String", "API_BASE_URL", "\"http://10.0.2.2:3000\"")
        buildConfigField("String", "API_BASE_URL", "\"http://192.168.1.69:3000\"")
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

    // Habilitar la generación de BuildConfig y el uso de Jetpack Compose
    buildFeatures {
        compose = true  // Habilita Jetpack Compose
        buildConfig = true  // Habilita BuildConfig para generar constantes
    }

    // Asegurarse de que tanto Java como Kotlin están en la misma versión
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8  // Usamos Java 1.8
        targetCompatibility = JavaVersion.VERSION_1_8  // Asegurar que también es 1.8
    }
    kotlinOptions {
        jvmTarget = "1.8"  // Kotlin apunta a Java 1.8
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
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.navigation.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Material Icons
    implementation("androidx.compose.material:material-icons-core:1.0.0")
    implementation("androidx.compose.material:material-icons-extended:1.0.0")

    // Retrofit para manejar las peticiones HTTP
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // OkHttp3 para las peticiones HTTP y el interceptor
    implementation("com.squareup.okhttp3:okhttp:4.9.1")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.1")
}
