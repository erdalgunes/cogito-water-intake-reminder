package com.cogito.convention

import org.gradle.api.Project
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.internal.catalog.LibrariesSourceGenerator
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

internal fun Project.configureAndroidApplication(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
){
    commonExtension.apply {

        dependencies {
            val libs = project.extensions.getByType<VersionCatalogsExtension>().named("libs")
            add("implementation", libs.findLibrary("annotation").get())
            add("implementation", libs.findLibrary("annotation.experimental").get())
            add("implementation", libs.findLibrary("ui.tooling.preview").get())
            add("implementation", libs.findLibrary("appcompat").get())
            add("implementation", libs.findLibrary("core").get())
            add("implementation", libs.findLibrary("core.ktx").get())
            add("implementation", libs.findLibrary("core.splashscreen").get())
            add("debugImplementation", libs.findLibrary("ui.tooling").get())
            add("debugImplementation", libs.findLibrary("ui.test.manifest").get())
        }
    }
}