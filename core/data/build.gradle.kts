plugins{
    alias(libs.plugins.cogito.android.library)
    alias(libs.plugins.cogito.android.library.jacoco)
    alias(libs.plugins.cogito.koin)
}

android {
    namespace = "com.cogito.data"
}

dependencies{
    api(projects.core.network)
    implementation(projects.core.database)
    implementation(projects.core.common)
    implementation(projects.core.model)

    implementation(libs.koin.android)
    implementation(libs.koin.android.compat)
}