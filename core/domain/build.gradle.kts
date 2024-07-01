plugins{
    alias(libs.plugins.cogito.android.library)
    alias(libs.plugins.cogito.android.library.jacoco)
    alias(libs.plugins.cogito.koin)
}

android {
    namespace = "com.cogito.domain"
}

dependencies {
    api(projects.core.common)
    api(projects.core.model)
    implementation(projects.core.data)
}