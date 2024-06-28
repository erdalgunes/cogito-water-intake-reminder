import java.io.FileInputStream
import java.util.Properties

plugins{
    alias(libs.plugins.cogito.android.library)
    alias(libs.plugins.cogito.android.library.jacoco)
    alias(libs.plugins.cogito.supabase)
    alias(libs.plugins.cogito.koin)
    alias(libs.plugins.cogito.ktor)
    kotlin("plugin.serialization") version libs.versions.kotlin
}

val prop = Properties().apply {
    load(FileInputStream(File(rootProject.rootDir, "local.properties")))
}

android {
    namespace = "com.cogito.network"

    defaultConfig{
        buildFeatures {
            buildConfig = true
        }
        buildConfigField("String", "SUPABASE_URL", prop.getProperty("SupabaseUrl"))
        buildConfigField("String", "SUPABASE_KEY", prop.getProperty("SupabaseKey"))
    }
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.common)

    implementation(libs.moshi)
    api(libs.kotlinx.datetime)
}