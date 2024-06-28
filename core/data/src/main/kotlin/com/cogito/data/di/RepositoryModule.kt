import com.cogito.data.repository.hydration.HydrationRepository
import com.cogito.data.repository.hydration.HydrationRepositoryImpl
import com.cogito.data.repository.user.UserRepository
import com.cogito.data.repository.user.UserRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::HydrationRepositoryImpl) { bind<HydrationRepository>() }
    singleOf(::UserRepositoryImpl) { bind<UserRepository>() }
}