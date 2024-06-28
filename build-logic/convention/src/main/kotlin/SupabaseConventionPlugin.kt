import com.cogito.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class SupabaseConventionPlugin  : Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            apply(plugin = "kotlin-android")

            dependencies {
                val supabaseBom = libs.findLibrary("supabase.bom").get()
                add("implementation", platform(supabaseBom))

                add("api", libs.findLibrary("supabase.postgrest").get())
                add("api", libs.findLibrary("supabase.realtime").get())
                add("api", libs.findLibrary("supabase.serializer.moshi").get())
            }
        }
    }
}