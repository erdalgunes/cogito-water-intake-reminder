import java.io.FileInputStream
import java.util.Properties
import com.cogito.convention.CogitoBuildType

plugins {
    alias(libs.plugins.cogito.android.wear)
    alias(libs.plugins.cogito.koin)
    alias(libs.plugins.cogito.ktor)
    alias(libs.plugins.cogito.circuit)
    alias(libs.plugins.cogito.supabase)
    alias(libs.plugins.cogito.compose)
    alias(libs.plugins.cogito.android.application)
    alias(libs.plugins.cogito.android.application.flavors)
    alias(libs.plugins.cogito.android.application.jacoco)
    alias(libs.plugins.roborazzi)

    id("kotlin-parcelize")
    kotlin("plugin.serialization") version libs.versions.kotlin
}

val prop = Properties().apply {
    load(FileInputStream(File(rootProject.rootDir, "local.properties")))
}

android {
    namespace = "com.cogito.hydration"

    defaultConfig {
        applicationId = "com.cogito.apps.hydration"
        versionCode = 1
        versionName = "0.0.1"

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
        debug {
            applicationIdSuffix = CogitoBuildType.DEBUG.applicationIdSuffix
        }

        release {
            isMinifyEnabled = true
            applicationIdSuffix = CogitoBuildType.RELEASE.applicationIdSuffix
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

            // To publish on the Play store a private signing key is required, but to allow anyone
            // who clones the code to sign and run the release variant, use the debug signing key.
            // TODO: Abstract the signing configuration to a separate file to avoid hardcoding this.
            signingConfig = signingConfigs.named("debug").get()
            // TODO: Enable baseline profile
            // Ensure Baseline Profile is fresh for release builds.
            //baselineProfile.automaticGenerationDuringBuild = true
        }
    }

    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.designsystem)

    implementation(libs.moshi)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.horologist.compose.tools)
    implementation(libs.koin.android)
    implementation(libs.koin.android.compat)
    implementation(libs.koin.compose)
    implementation(libs.koin.androidx.compose)
}