import com.cogito.convention.configureAndroidWear
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.android.build.gradle.BaseExtension
import com.cogito.convention.configureBadgingTasks
import com.cogito.convention.configureKotlinAndroid
import com.cogito.convention.configurePrintApksTask
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin  : Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            apply(plugin = "com.android.application")
            apply(plugin = "kotlin-android")
            apply(plugin = "cogito.android.lint")

            val extension = extensions.getByType<ApplicationExtension>()
            configureKotlinAndroid(extension)

            extensions.configure<ApplicationAndroidComponentsExtension> {
                configurePrintApksTask(this)
                configureBadgingTasks(extensions.getByType<BaseExtension>(), this)
            }
        }
    }
}