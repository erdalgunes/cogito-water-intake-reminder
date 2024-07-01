import com.cogito.domain.AddHydrationUseCase
import com.cogito.domain.AuthenticateUserUseCase
import com.cogito.domain.GetHydrationSummaryForTodayUseCase
import com.cogito.domain.GetUserHydrationGoalUseCase
import com.cogito.domain.SyncUserHydrationGoalUseCase
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

fun domainModule() = domainModule + repositoryModule()

internal val domainModule = module {
    single {
        AddHydrationUseCase(
            hydrationRepository = get(),
            userRepository = get(),
            log = get(parameters = { parametersOf("AddHydrationUseCase") })
        )
    }
    single {
        AuthenticateUserUseCase(
            userRepository = get(),
            log = get(parameters = { parametersOf("AuthenticateUserUseCase") })
        )
    }
    single {
        GetHydrationSummaryForTodayUseCase(
            hydrationRepository = get(),
            log = get(parameters = { parametersOf("GetHydrationSummaryForTodayUseCase") })
        )
    }
    single {
        GetUserHydrationGoalUseCase(
            userRepository = get(),
            log = get(parameters = { parametersOf("GetUserHydrationGoalUseCase") })
        )
    }
    single {
        SyncUserHydrationGoalUseCase(
            userRepository = get(),
            log = get(parameters = { parametersOf("SyncUserHydrationGoalUseCase") })
        )
    }
}