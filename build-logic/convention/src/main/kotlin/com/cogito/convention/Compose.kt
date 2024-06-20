package com.cogito.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

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
            val libs = project.extensions.getByType<VersionCatalogsExtension>().named("libs")

            val composeBom = libs.findLibrary("compose.bom").get()
            add("implementation", platform(composeBom))
            add("androidTestImplementation", platform(composeBom))

            add("implementation", libs.findLibrary("activity.compose").get())
            add("implementation", libs.findLibrary("compose.foundation").get())
            add("implementation", libs.findLibrary("compose.material").get())
            add("implementation", libs.findLibrary("lifecycle.viewmodel.compose").get())
            add("implementation", libs.findLibrary("horologist.compose.tools").get())
            add("implementation", libs.findLibrary("compose.wear.ui.tooling").get())

            add("androidTestImplementation", libs.findLibrary("ui.test.junit4").get())
        }
    }

//    extensions.configure<ComposeCompilerGradlePluginExtension> {
//        version = "1.5.14"
//    }

}