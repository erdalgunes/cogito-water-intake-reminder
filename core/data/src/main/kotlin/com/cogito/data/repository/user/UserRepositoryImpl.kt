package com.cogito.data.repository.user

import android.util.Log
import co.touchlab.kermit.Logger
import com.cogito.core.DoNothing
import com.cogito.core.concurrency.CogitoDispatchers
import com.cogito.database.dao.UserDao
import com.cogito.database.model.UserEntity
import com.cogito.network.datasource.user.UserNetworkDataSource
import io.github.jan.supabase.exceptions.RestException
import io.github.jan.supabase.gotrue.SessionStatus
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.core.qualifier.named
import org.koin.mp.KoinPlatform.getKoin
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

internal class UserRepositoryImpl(
    private val dao: UserDao,
    private val dataSource: UserNetworkDataSource,
    private val log: Logger,
) : UserRepository {
    private val coroutineScope: CoroutineScope = getKoin().get(named<CogitoDispatchers.IO>())
    override suspend fun authenticateUser(): String = suspendCoroutine { continuation ->
        coroutineScope.launch {
            try {
                log.d("authenticateUser(): Checking session...")
                val userId = dataSource.getAuthenticatedUserId()
                log.d("authenticateUser(): Session found. User ID: $userId")
                continuation.resume(userId)
            } catch (e: Exception) {
                when (e) {
                    is RestException,
                    is IllegalStateException -> {
                        log.e("authenticateUser(): No session found: ", e)
                        log.d("authenticateUser(): Authenticating user...")
                        dataSource.observeUserAuth()
                            .onEach { sessionResult ->
                                when (sessionResult) {
                                    is SessionStatus.Authenticated -> {
                                        log.d("authenticateUser(): User authenticated")
                                        val userId = sessionResult.session.user?.id
                                        log.d("authenticateUser(): User ID: $userId")
                                        if (userId != null) {
                                            log.d("authenticateUser(): Saving user...")
                                            dao.insertUser(UserEntity(userId))
                                            log.d("authenticateUser(): User saved")
                                            continuation.resume(userId)
                                        }
                                    }

                                    is SessionStatus.NotAuthenticated -> {
                                        log.d("authenticateUser(): User not authenticated")
                                        log.d("authenticateUser(): Authenticating user..."
                                        )
                                        dataSource.authenticateUser()
                                        log.d("authenticateUser(): User authenticated")
                                    }

                                    SessionStatus.NetworkError -> {
                                        continuation.resumeWithException(IOException("Network error"))
                                    }

                                    else -> DoNothing
                                }
                            }
                            .launchIn(this)
                    }

                    else -> continuation.resumeWithException(e)
                }
            }
        }
    }

    override suspend fun getUserHydrationGoal(): Int = dao.getUserGoalInMilliliters()

    override suspend fun syncUserHydrationGoal(userId: String, goal: Int) {
        dataSource.syncUserHydrationGoal(userId, goal)
    }
}