import com.cogito.core.concurrency.di.coroutineScopesModule
import com.cogito.core.concurrency.di.dispatchersModule

fun coreModule() = listOf(dispatchersModule, coroutineScopesModule, loggingModule)

