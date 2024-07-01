plugins{
    alias(libs.plugins.cogito.android.library)
    alias(libs.plugins.cogito.android.library.jacoco)
    alias(libs.plugins.cogito.koin)
}

android {
    namespace = "com.cogito.analytics"
}

dependencies {
    implementation(projects.core.common)

    prodImplementation(platform(libs.firebase.bom))
    prodImplementation(libs.firebase.analytics)
}