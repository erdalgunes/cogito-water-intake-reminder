import com.cogito.data.repository.HydrationRepository
import com.cogito.data.repository.HydrationRepositoryImpl
import com.cogito.data.repository.UserRepository
import com.cogito.data.repository.UserRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::HydrationRepositoryImpl) { bind<HydrationRepository>() }
    singleOf(::UserRepositoryImpl) { bind<UserRepository>() }
}