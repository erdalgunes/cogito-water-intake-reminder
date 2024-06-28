package com.cogito.hydration.summary.presentation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.cogito.core.network.CogitoDispatchers
import com.cogito.data.repository.hydration.HydrationRepository
import com.cogito.data.repository.user.UserRepository
import com.cogito.hydration.summary.presentation.state.SummaryEvent
import com.cogito.hydration.summary.presentation.state.SummaryState
import com.cogito.model.ui.HydrationSummary
import com.slack.circuit.runtime.presenter.Presenter
import io.github.jan.supabase.exceptions.HttpRequestException
import io.github.jan.supabase.exceptions.RestException
import io.ktor.client.plugins.HttpRequestTimeoutException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.future.await
import kotlinx.coroutines.future.future
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import org.koin.core.qualifier.named

class SummaryPresenter(
    private val hydrationRepository: HydrationRepository,
    private val userRepository: UserRepository,
) : Presenter<SummaryState> {
    @Composable
    override fun present(): SummaryState {
        val ioDispatcher: CoroutineDispatcher = koinInject(named<CogitoDispatchers.IO>())
        val coroutineScope = rememberCoroutineScope()
        var hydrationSummary by remember { mutableStateOf<HydrationSummary?>(null) }
        var isLoading by remember { mutableStateOf(true) }
        var loadingAmount by remember { mutableIntStateOf(500) }
        var isError by remember { mutableStateOf(false) }
        var userId by remember { mutableStateOf("") }

        LaunchedEffect(isError) {
            coroutineScope.launch(ioDispatcher) {
                try {
                    userId = future {
                        userRepository.authenticateUser()
                    }.await()
                } catch (e: Exception) {
                    Log.e("SummaryPresenter", "present: ", e)
                }
                try {
                    Log.d("SummaryPresenter", "present: Getting hydration goal")
                    val userHydrationGoal = userRepository.getUserHydrationGoal()
                    Log.d("SummaryPresenter", "present: Got hydration goal: $userHydrationGoal")
                    try {
                        Log.d("SummaryPresenter", "present: Syncing hydration goal")
                        userRepository.syncUserHydrationGoal(userId, userHydrationGoal)
                    } catch (e: Exception) {
                        Log.e("SummaryPresenter", "present: Error syncing hydration goal", e)
                    }
                    Log.d("SummaryPresenter", "present: Getting hydration summary")
                    hydrationRepository.getHydrationSummaryToday().onEach { summary ->
                        Log.d("SummaryPresenter", "present: Got hydration summary: $summary")
                        isLoading = true
                        hydrationSummary = HydrationSummary(
                            summary,
                            userHydrationGoal,
                        )
                        isLoading = false
                    }.launchIn(this)
                } catch (e: Exception) {
                    Log.e("SummaryPresenter", "present: ", e)
                    isError = true
                }
            }
        }

        return SummaryState(
            isError = isError,
            hydrationGoal = hydrationSummary?.hydrationGoal,
            hydrationToday = hydrationSummary?.hydrationToday,
            isLoading = isLoading,
            loadingAmount = loadingAmount
        ) { event ->
            when (event) {
                is SummaryEvent.AddHydration -> {
                    isLoading = true
                    loadingAmount = event.hydration.amountMilliliters
                    coroutineScope.launch(ioDispatcher) {
                        try {
                            hydrationRepository.addHydration(
                                event.hydration,
                                userId,
                            )
                        } catch (e: Exception) {
                            when (e) {
                                is RestException,
                                is HttpRequestTimeoutException,
                                is HttpRequestException -> {
                                    Log.e("SummaryPresenter", "present: Couldn't sync data", e)
                                }

                                else -> {
                                    Log.e("SummaryPresenter", "present: ", e)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}