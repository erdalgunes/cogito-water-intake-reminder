plugins{
    alias(libs.plugins.cogito.android.feature.wear)
    alias(libs.plugins.cogito.android.library)
    alias(libs.plugins.cogito.android.library.jacoco)
}

android {
    namespace = "com.cogito.hydration.wear.summary"
}

dependencies{
    implementation(projects.core.domain)

    implementation(libs.koin.compose)
}