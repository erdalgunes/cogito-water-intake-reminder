import java.io.FileInputStream
import java.util.Properties
import com.cogito.convention.CogitoBuildType

plugins {
    alias(libs.plugins.cogito.android.application)
    alias(libs.plugins.cogito.android.application.flavors)
    alias(libs.plugins.cogito.android.application.jacoco)
    alias(libs.plugins.cogito.android.application.compose)
    alias(libs.plugins.cogito.koin)
    alias(libs.plugins.cogito.circuit)
    alias(libs.plugins.roborazzi)
}

android {
    namespace = "com.cogito.wear.hydration"

    defaultConfig {
        applicationId = "com.cogito.apps.tracker.wear.hydration"
        versionCode = 1
        versionName = "0.0.1"

        vectorDrawables {
            useSupportLibrary = true
        }
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
    implementation(projects.core.data)
    implementation(projects.core.database)
    implementation(projects.core.designsystem.android)
    implementation(projects.core.designsystem.wear)
    implementation(projects.core.designsystem.common)
    implementation(projects.feature.hydration.wear.summary)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.splashscreen)

    implementation(libs.koin.android.compat)
    implementation(libs.koin.androidx.compose)
}