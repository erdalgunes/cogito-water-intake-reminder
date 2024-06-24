import com.cogito.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KoinConventionPlugin  : Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            with(pluginManager){
                apply("com.google.devtools.ksp")
            }
            dependencies {
                add("implementation", libs.findLibrary("koin.annotations").get())
                add("ksp", libs.findLibrary("koin.ksp.compiler").get())

                val koinBom = libs.findLibrary("koin.bom").get()
                add("implementation", platform(koinBom))
                add("implementation", libs.findLibrary("koin.core").get())
                add("implementation", libs.findLibrary("koin.core.coroutines").get())
            }
        }
    }
}