package com.cogito.convention

import org.gradle.api.Project
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.internal.catalog.LibrariesSourceGenerator
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

internal fun Project.configureAndroidWear(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
){
    commonExtension.apply {

        dependencies {
            add("implementation", libs.findLibrary("tiles").get())
            add("implementation", libs.findLibrary("tiles.material").get())
            add("implementation", libs.findLibrary("play.services.wearable").get())
            add("implementation", libs.findLibrary("horologist.tiles").get())
        }
    }
}