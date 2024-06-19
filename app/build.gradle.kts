import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-parcelize")
    kotlin("plugin.serialization") version libs.versions.kotlin
}

val prop = Properties().apply {
    load(FileInputStream(File(rootProject.rootDir, "local.properties")))
}

android {
    namespace = "com.cogito.water"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.cogito.water"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildFeatures{
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
        kotlinCompilerExtensionVersion = "1.5.14"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(platform(libs.compose.bom))
    implementation(platform(libs.koin.annotations.bom))
    implementation(platform(libs.koin.bom))
    implementation(platform(libs.supabase.bom))

    implementation(libs.activity.compose)
    implementation(libs.androidx.annotation)
    implementation(libs.androidx.annotation.experimental)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.circuit)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material)
    implementation(libs.compose.wear.ui.tooling)
    implementation(libs.core.splashscreen)
    implementation(libs.horologist.compose.tools)
    implementation(libs.horologist.tiles)
    implementation(libs.koin.android)
    implementation(libs.koin.android.compat)
    implementation(libs.koin.androidx.compose)
    implementation(libs.koin.annotations)
    implementation(libs.koin.compose)
    implementation(libs.koin.core)
    implementation(libs.koin.core.coroutines)
    implementation(libs.play.services.wearable)
    implementation(libs.tiles)
    implementation(libs.tiles.material)
    implementation(libs.androidx.wear)
    implementation(libs.androidx.wear.tooling.preview)
    implementation(libs.ui.tooling.preview)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.serialization.json)
    implementation(libs.supabase.realtime)
    implementation(libs.supabase.postgrest)
    implementation(libs.supabase.serializer.moshi)
    implementation(libs.moshi)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
}