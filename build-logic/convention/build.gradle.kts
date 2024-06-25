import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.cogito.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_11
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
        register("androidApplicationJacoco") {
            id = "cogito.android.application.jacoco"
            implementationClass = "AndroidApplicationJacocoConventionPlugin"
        }
        register("androidApplicationFlavors") {
            id = "cogito.android.application.flavors"
            implementationClass = "AndroidApplicationFlavorsConventionPlugin"
        }
        register("androidLint") {
            id = "cogito.android.lint"
            implementationClass = "AndroidLintConventionPlugin"
        }
        register("androidLibrary") {
            id = "cogito.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "cogito.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidLibraryJacoco") {
            id = "cogito.android.library.jacoco"
            implementationClass = "AndroidLibraryJacocoConventionPlugin"
        }
        register("jvmLibrary") {
            id = "cogito.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }
        register("androidLibraryGlance"){
            id = "cogito.android.library.glance"
            implementationClass = "AndroidLibraryGlanceConventionPlugin"
        }
    }
}