import com.android.build.api.dsl.ApplicationExtension
import com.cogito.convention.configureSupabase
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType

class SupabaseConventionPlugin  : Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            apply(plugin = "com.android.application")
            apply(plugin = "kotlin-android")

            val extension = extensions.getByType<ApplicationExtension>()
            configureSupabase(extension)
        }
    }
}