plugins{
    alias(libs.plugins.cogito.android.library)
    alias(libs.plugins.cogito.android.library.compose)
    alias(libs.plugins.cogito.android.library.jacoco)
}

android {
    namespace = "com.cogito.designsystem.wear"
}

dependencies {
    lintPublish(projects.lint)

    api(libs.androidx.wear)
    api(libs.androidx.wear.tooling.preview)
}