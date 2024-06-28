import com.android.build.gradle.LibraryExtension
import com.cogito.convention.configureAndroidWear
import com.cogito.convention.configureCompose
import com.cogito.convention.configureGradleManagedDevices
import com.cogito.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidFeatureWearConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("cogito.android.library")
                apply("cogito.koin")
                apply("cogito.circuit")
                apply("kotlin-parcelize")
            }
            apply(plugin = "org.jetbrains.kotlin.plugin.compose")

            extensions.configure<LibraryExtension> {
                // TODO(): Refactor this
//                defaultConfig {
//                    testInstrumentationRunner =
//                        "com.google.samples.apps.cogito.core.testing.NiaTestRunner"
//                }
                testOptions.animationsDisabled = true
                configureGradleManagedDevices(this)
                configureCompose(this)
                configureAndroidWear(this)
            }

            dependencies {
                add("implementation", project(":core:ui"))
                add("implementation", project(":core:designsystem:android"))
                add("implementation", project(":core:designsystem:wear"))

                add("implementation", libs.findLibrary("androidx.tracing.ktx").get())

                // TODO(): Enable testing
                // add("androidTestImplementation", libs.findLibrary("androidx.lifecycle.runtimeTesting").get())
            }
        }
    }
}