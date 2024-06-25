plugins {
    alias(libs.plugins.cogito.android.library)
    alias(libs.plugins.cogito.android.library.jacoco)
    alias(libs.plugins.cogito.android.library.glance)
}

android {
    namespace = "com.cogito.tile.hydration"
}

dependencies {
    api(libs.androidx.glance.wear.tiles)
}