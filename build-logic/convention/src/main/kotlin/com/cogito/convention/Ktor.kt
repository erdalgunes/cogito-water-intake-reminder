package com.cogito.convention

import org.gradle.api.Project
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.internal.catalog.LibrariesSourceGenerator
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

internal fun Project.configureKtor(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
){
    commonExtension.apply {
        dependencies {
            val libs = project.extensions.getByType<VersionCatalogsExtension>().named("libs")
            add("implementation", libs.findLibrary("ktor.client.cio").get())
            add("implementation", libs.findLibrary("ktor.serialization.json").get())
            add("implementation", libs.findLibrary("ktor.client.core").get())
            add("implementation", libs.findLibrary("ktor.client.content.negotiation").get())
            add("implementation", libs.findLibrary("ktor.client.websockets").get())
            add("implementation", libs.findLibrary("ktor.client.mock").get())
            add("implementation", libs.findLibrary("ktor.server.core").get())
            add("implementation", libs.findLibrary("ktor.server.cio").get())
            add("implementation", libs.findLibrary("ktor.server.content.negotiation").get())
        }
    }
}