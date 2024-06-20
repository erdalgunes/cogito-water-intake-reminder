import com.android.build.api.dsl.ApplicationExtension
import com.cogito.convention.configureCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType

class ComposeConventionPlugin  : Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            apply(plugin = "com.android.application")
            apply(plugin = "kotlin-android")

            val extension = extensions.getByType<ApplicationExtension>()
            configureCompose(extension)
        }
    }
}