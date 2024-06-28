plugins{
    alias(libs.plugins.cogito.android.library)
    alias(libs.plugins.cogito.android.library.jacoco)
    alias(libs.plugins.cogito.android.room)
    alias(libs.plugins.cogito.koin)
}

android {
    namespace = "com.cogito.database"
}

dependencies {
    api(projects.core.model)
    api(projects.core.common)

    implementation(libs.kotlinx.datetime)

    //androidTestImplementation(projects.core.testing)
}