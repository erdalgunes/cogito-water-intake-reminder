import com.android.build.api.dsl.CommonExtension
import com.cogito.convention.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureGlance(
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
            add( "implementation", libs.findLibrary("androidx.glance").get())
            add( "implementation", libs.findLibrary("androidx.glance.preview").get())
        }
    }
}