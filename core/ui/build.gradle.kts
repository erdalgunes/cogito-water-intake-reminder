plugins{
    alias(libs.plugins.cogito.android.library)
    alias(libs.plugins.cogito.android.library.jacoco)
}

android {
    namespace = "com.cogito.ui"
}

dependencies {
    api(projects.core.designsystem.android)
    api(projects.core.model)
}