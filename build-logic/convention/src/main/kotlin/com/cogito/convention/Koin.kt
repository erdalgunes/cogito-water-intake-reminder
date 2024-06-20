package com.cogito.convention

import org.gradle.api.Project
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.internal.catalog.LibrariesSourceGenerator
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

internal fun Project.configureKoin(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        dependencies {
            val libs = project.extensions.getByType<VersionCatalogsExtension>().named("libs")

            val koinAnnotationsBom = libs.findLibrary("koin.annotations.bom").get()
            add("implementation", platform(koinAnnotationsBom))

            val koinBom = libs.findLibrary("koin.bom").get()
            add("implementation", platform(koinBom))

            add("implementation", libs.findLibrary("koin.android").get())
            add("implementation", libs.findLibrary("koin.android.compat").get())
            add("implementation", libs.findLibrary("koin.androidx.compose").get())
            add("implementation", libs.findLibrary("koin.annotations").get())

            val koinCompose = libs.findLibrary("koin.compose").get()
            add("implementation", koinCompose)
            add("implementation", koinCompose)

            add("implementation", libs.findLibrary("koin.core").get())
            add("implementation", libs.findLibrary("koin.core.coroutines").get())
        }
    }
}