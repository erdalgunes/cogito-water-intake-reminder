package com.cogito.domain

import co.touchlab.kermit.Logger
import com.cogito.data.repository.hydration.HydrationRepository
import com.cogito.data.repository.user.UserRepository
import com.cogito.model.data.HydrationDataModel
import io.github.jan.supabase.exceptions.HttpRequestException
import io.github.jan.supabase.exceptions.RestException
import io.ktor.client.plugins.HttpRequestTimeoutException
import org.koin.core.parameter.parametersOf
import org.koin.mp.KoinPlatform.getKoin

class AddHydrationUseCase(
    private val hydrationRepository: HydrationRepository,
    private val userRepository: UserRepository,
    private val log: Logger,
) {
    suspend operator fun invoke(hydration: HydrationDataModel) {
        try {
            hydrationRepository.addHydration(hydration, userRepository.getUserId())
        } catch (e: Exception) {
            when (e) {
                is RestException,
                is HttpRequestTimeoutException,
                is HttpRequestException -> {
                    log.e("Couldn't sync data", e)
                }

                else -> {
                    log.e("Unexpected error", e)
                }
            }
        }
    }

}