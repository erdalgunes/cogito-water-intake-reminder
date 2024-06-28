plugins{
    alias(libs.plugins.cogito.android.library)
    alias(libs.plugins.cogito.android.library.jacoco)
}

android {
    namespace = "com.cogito.model"
}

dependencies {
    api(libs.kotlinx.datetime)
}