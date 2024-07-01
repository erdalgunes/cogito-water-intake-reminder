import co.touchlab.kermit.Logger
import co.touchlab.kermit.StaticConfig
import cogito.core.common.BuildConfig
import com.cogito.core.logging.CogitoLogWriter
import org.koin.dsl.module

internal val loggingModule =  module {
    val baseLogger = Logger(
        config = StaticConfig(
            logWriterList = listOf(CogitoLogWriter(isProduction = !BuildConfig.DEBUG)),
        ),
        tag = "Cogito"
    )
    factory { (tag: String?) -> if (tag != null) baseLogger.withTag(tag) else baseLogger }
}