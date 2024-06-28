import com.cogito.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KtorConventionPlugin  : Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            dependencies {
                add("implementation", libs.findLibrary("ktor.client.cio").get())
                add("implementation", libs.findLibrary("ktor.serialization.json").get())
            }
        }
    }
}