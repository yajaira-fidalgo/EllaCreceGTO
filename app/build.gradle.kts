plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    // Plugin necesario para sincronizar Firebase con el archivo google-services.json
    id("com.google.gms.google-services")
}

android {
    namespace = "com.yajaira.crece"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.yajaira.crece"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // --- INTEGRACIÓN DE FIREBASE Y VIEWMODEL ---
    // Firebase BoM (gestiona automáticamente las versiones compatibles)
    implementation(platform("com.google.firebase:firebase-bom:33.7.0"))

    // Cloud Firestore (Base de datos en la nube)
    implementation("com.google.firebase:firebase-firestore-ktx")

    // Firebase Authentication (Inicio de sesión y registros reales)
    implementation("com.google.firebase:firebase-auth-ktx")

    // ViewModel para conectar el estado de Compose con Firebase
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")

    // Pruebas e instrumentación
    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)

    implementation("com.google.firebase:firebase-firestore-ktx:25.1.4")
}