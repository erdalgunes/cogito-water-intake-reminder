package com.cogito.domain

import co.touchlab.kermit.Logger
import com.cogito.data.repository.hydration.HydrationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import org.koin.core.parameter.parametersOf
import org.koin.mp.KoinPlatform.getKoin

class GetHydrationSummaryForTodayUseCase(
    private val hydrationRepository: HydrationRepository,
    private val log: Logger = getKoin().get(parameters = { parametersOf("GetHydrationSummaryForTodayUseCase") })
) {
    operator fun invoke(): Flow<Int> {
        return hydrationRepository.getHydrationSummaryToday().onEach { summary ->
            log.d("present: Got hydration summary: $summary")
        }
    }
}