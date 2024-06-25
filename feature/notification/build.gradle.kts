plugins{
    alias(libs.plugins.cogito.android.library)
    alias(libs.plugins.cogito.android.library.compose)
    alias(libs.plugins.cogito.android.library.jacoco)
}

android {
    namespace = "com.cogito.notification"
}

dependencies{
    implementation(libs.accompanist.permissions)
    implementation(libs.androidx.appcompat)
}