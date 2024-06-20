import com.cogito.hydration.di.CircuitProvider
import com.cogito.hydration.di.CircuitProviderImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::CircuitProviderImpl){ bind<CircuitProvider>() }
}