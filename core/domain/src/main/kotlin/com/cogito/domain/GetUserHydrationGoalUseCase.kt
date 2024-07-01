package com.cogito.domain

import co.touchlab.kermit.Logger
import com.cogito.data.repository.user.UserRepository
import org.koin.core.parameter.parametersOf
import org.koin.mp.KoinPlatform.getKoin

class GetUserHydrationGoalUseCase(
    private val userRepository: UserRepository,
    private val log: Logger = getKoin().get<Logger>(parameters = { parametersOf("GetUserHydrationGoalUseCase") })
) {
    suspend operator fun invoke(): Int {
        log.d("Getting hydration goal")
        val userHydrationGoal = userRepository.getUserHydrationGoal()
        log.d("Got hydration goal: $userHydrationGoal")
        return userHydrationGoal
    }
}