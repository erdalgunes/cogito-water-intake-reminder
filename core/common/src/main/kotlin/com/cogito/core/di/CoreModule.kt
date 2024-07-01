import com.cogito.core.concurrency.di.coroutineScopesModule
import com.cogito.core.concurrency.di.dispatchersModule

val coreModule = listOf(dispatchersModule, coroutineScopesModule, loggingModule)

