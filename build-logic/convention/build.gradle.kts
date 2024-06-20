import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.cogito.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    implementation(libs.truth)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("androidWear") {
            id = "cogito.android.wear"
            implementationClass = "AndroidWearConventionPlugin"
        }
        register("koin") {
            id = "cogito.koin"
            implementationClass = "KoinConventionPlugin"
        }
        register("circuit") {
            id = "cogito.circuit"
            implementationClass = "CircuitConventionPlugin"
        }
        register("ktor") {
            id = "cogito.ktor"
            implementationClass = "KtorConventionPlugin"
        }
        register("compose") {
            id = "cogito.compose"
            implementationClass = "ComposeConventionPlugin"
        }
        register("supabase") {
            id = "cogito.supabase"
            implementationClass = "SupabaseConventionPlugin"
        }
        register("androidApplication") {
            id = "cogito.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
    }
}