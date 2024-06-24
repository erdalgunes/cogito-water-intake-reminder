package com.cogito.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension

internal fun Project.configureCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = "1.5.14"
        }

        dependencies {
            val composeBom = libs.findLibrary("androidx.compose.bom").get()
            add("implementation", platform(composeBom))
            add("androidTestImplementation", platform(composeBom))

            add( "implementation", libs.findLibrary("androidx.compose.ui.tooling.preview").get())
            add( "debugImplementation", libs.findLibrary("androidx.compose.ui.tooling").get())

            add("androidTestImplementation", libs.findLibrary("ui.test.junit4").get())
        }

        testOptions {
            unitTests {
                // For Robolectric
                isIncludeAndroidResources = true
            }
        }
    }

//    extensions.configure<ComposeCompilerGradlePluginExtension> {
//        fun Provider<String>.onlyIfTrue() = flatMap { provider { it.takeIf(String::toBoolean) } }
//        fun Provider<*>.relativeToRootProject(dir: String) = flatMap {
//            rootProject.layout.buildDirectory.dir(projectDir.toRelativeString(rootDir))
//        }.map { it.dir(dir) }
//
//        project.providers.gradleProperty("enableComposeCompilerMetrics").onlyIfTrue()
//            .relativeToRootProject("compose-metrics")
//            .let(metricsDestination::set)
//
//        project.providers.gradleProperty("enableComposeCompilerReports").onlyIfTrue()
//            .relativeToRootProject("compose-reports")
//            .let(reportsDestination::set)
//
//        // TODO(): Try enabling these
////        stabilityConfigurationFile =
////            rootProject.layout.projectDirectory.file("compose_compiler_config.conf")
////
////        enableStrongSkippingMode = true
//    }

}