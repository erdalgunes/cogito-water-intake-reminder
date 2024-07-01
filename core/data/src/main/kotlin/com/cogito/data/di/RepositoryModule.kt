import com.cogito.data.repository.hydration.HydrationRepository
import com.cogito.data.repository.hydration.HydrationRepositoryImpl
import com.cogito.data.repository.user.UserRepository
import com.cogito.data.repository.user.UserRepositoryImpl
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val repositoryModule = module {
    single<HydrationRepository> {
        HydrationRepositoryImpl(
            dao = get(),
            hydrationDataSource = get(),
            log = get(parameters = { parametersOf("HydrationRepositoryImpl") })
        )
    }
    single<UserRepository> {
        UserRepositoryImpl(
            dao = get(),
            dataSource = get(),
            log = get(parameters = { parametersOf("UserRepositoryImpl") })
        )
    }
}