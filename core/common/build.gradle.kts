plugins {
    alias(libs.plugins.cogito.android.library)
    alias(libs.plugins.cogito.android.library.jacoco)
    alias(libs.plugins.cogito.koin)
}

android {
    namespace = "cogito.core.common"

    defaultConfig {
        buildFeatures {
            buildConfig = true
        }
    }
}

dependencies {
    api(libs.kermit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
}