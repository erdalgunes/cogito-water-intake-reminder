package com.cogito.convention

import org.gradle.api.Project
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.internal.catalog.LibrariesSourceGenerator
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

internal fun Project.configureSupabase(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        dependencies {
            val supabaseBom = libs.findLibrary("supabase.bom").get()
            add("implementation", platform(supabaseBom))

            add("implementation", libs.findLibrary("supabase.postgrest").get())
            add("implementation", libs.findLibrary("supabase.realtime").get())
            add("implementation", libs.findLibrary("supabase.serializer.moshi").get())
        }
    }
}