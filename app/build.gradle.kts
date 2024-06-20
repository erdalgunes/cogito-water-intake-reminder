import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.cogito.android.wear)
    alias(libs.plugins.cogito.koin)
    alias(libs.plugins.cogito.ktor)
    alias(libs.plugins.cogito.circuit)
    alias(libs.plugins.cogito.supabase)
    alias(libs.plugins.cogito.compose)
    alias(libs.plugins.cogito.android.application)

    id("kotlin-parcelize")
    kotlin("plugin.serialization") version libs.versions.kotlin
}

val prop = Properties().apply {
    load(FileInputStream(File(rootProject.rootDir, "local.properties")))
}

android {
    namespace = "com.cogito.hydration"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.cogito.hydration"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildFeatures {
            buildConfig = true
        }

        buildConfigField("String", "SUPABASE_URL", prop.getProperty("SupabaseUrl"))
        buildConfigField("String", "SUPABASE_KEY", prop.getProperty("SupabaseKey"))
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

    kotlinOptions {
        jvmTarget = "11"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.moshi)
}