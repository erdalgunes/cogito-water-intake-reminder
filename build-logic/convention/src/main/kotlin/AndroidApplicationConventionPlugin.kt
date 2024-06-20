import com.cogito.convention.configureAndroidWear
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType
import com.android.build.api.dsl.ApplicationExtension
import com.cogito.convention.configureKotlinAndroid

class AndroidApplicationConventionPlugin  : Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            apply(plugin = "com.android.application")
            apply(plugin = "kotlin-android")
            apply(plugin = "cogito.android.lint")

            val extension = extensions.getByType<ApplicationExtension>()
            configureKotlinAndroid(extension)
        }
    }
}