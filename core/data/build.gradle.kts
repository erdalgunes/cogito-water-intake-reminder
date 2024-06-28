plugins{
    alias(libs.plugins.cogito.android.library)
    alias(libs.plugins.cogito.android.library.jacoco)
    alias(libs.plugins.cogito.koin)
}

android {
    namespace = "com.cogito.data"
}

dependencies{
    api(projects.core.common)
    api(projects.core.database)
    api(projects.core.model)
    api(projects.core.network)

    implementation(libs.koin.android)
    implementation(libs.koin.android.compat)
}