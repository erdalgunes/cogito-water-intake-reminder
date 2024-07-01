package com.cogito.domain

import co.touchlab.kermit.Logger
import com.cogito.data.repository.user.UserRepository
import org.koin.core.parameter.parametersOf
import org.koin.mp.KoinPlatform.getKoin

class SyncUserHydrationGoalUseCase(
    private val userRepository: UserRepository,
    private val log: Logger = getKoin().get(parameters = { parametersOf("SyncUserHydrationGoalUseCase") })
) {
    suspend operator fun invoke() {
        try {
            log.d("Syncing hydration goal")
            userRepository.syncUserHydrationGoal(userRepository.getUserId(), userRepository.getUserHydrationGoal())
        } catch (e: Exception) {
            log.e("Error syncing hydration goal", e)
        }
    }
}