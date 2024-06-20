plugins {
    alias(libs.plugins.cogito.android.library)
    alias(libs.plugins.cogito.android.library.compose)
    alias(libs.plugins.cogito.android.library.jacoco)
    alias(libs.plugins.roborazzi)
}

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    namespace = "cogito.core.designsystem"
}

dependencies{
    lintPublish(projects.lint)

    api(libs.androidx.compose.foundation)
    api(libs.androidx.wear.compose.foundation)
    api(libs.androidx.wear.compose.material)
    api(libs.androidx.wear.compose.navigation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.wear.compose.material)
    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.material3.adaptive)
    api(libs.androidx.compose.material3.navigationSuite)
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.ui.util)

    // TODO(): Uncomment when implemented
//    implementation(libs.coil.kt.compose)

    testImplementation(libs.androidx.compose.ui.test)
    testImplementation(libs.androidx.compose.ui.testManifest)
    testImplementation(libs.robolectric)
    testImplementation(libs.roborazzi)

    // TODO(): Uncomment when implemented
//    testImplementation(projects.core.screenshotTesting)
//    testImplementation(projects.core.testing)

    androidTestImplementation(libs.androidx.compose.ui.test)
//    androidTestImplementation(projects.core.testing)
}

