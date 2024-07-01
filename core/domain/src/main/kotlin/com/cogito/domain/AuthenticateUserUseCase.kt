package com.cogito.domain

import co.touchlab.kermit.Logger
import com.cogito.data.repository.user.UserRepository
import org.koin.core.parameter.parametersOf
import org.koin.mp.KoinPlatform.getKoin

class AuthenticateUserUseCase(
    private val userRepository: UserRepository,
    private val log: Logger = getKoin().get<Logger>(parameters = { parametersOf("AuthenticateUserUseCase") })
) {
    suspend operator fun invoke(){
        try {
            userRepository.authenticateUser()
        } catch (e: Exception) {
            log.e("Couldn't authenticate user", e)
        }
    }
}