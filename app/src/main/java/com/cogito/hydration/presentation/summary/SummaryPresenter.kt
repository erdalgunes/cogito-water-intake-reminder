package com.cogito.hydration.presentation.summary

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.cogito.core.network.CogitoDispatchers
import com.cogito.hydration.data.model.DailyHydrationIntake
import com.cogito.hydration.data.repository.HydrationRepository
import com.cogito.hydration.presentation.summary.state.SummaryEvent
import com.cogito.hydration.presentation.summary.state.SummaryState
import com.slack.circuit.runtime.presenter.Presenter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import org.koin.core.qualifier.named

class SummaryPresenter(
    private val waterIntakeRepository: HydrationRepository,
) : Presenter<SummaryState> {
    @Composable
    override fun present(): SummaryState {
        val ioDispatcher: CoroutineDispatcher = koinInject(named<CogitoDispatchers.IO>())
        val lifecycleOwner = LocalLifecycleOwner.current
        val coroutineScope = rememberCoroutineScope()
        var waterIntake by remember { mutableStateOf<DailyHydrationIntake?>(null) }
        var isLoading by remember { mutableStateOf(true) }
        var loadingAmount by remember { mutableIntStateOf(500) }
        var isError by remember { mutableStateOf(false) }

        DisposableEffect(Unit) {
            val observer = LifecycleEventObserver { _, event ->
                when (event) {
                    Lifecycle.Event.ON_START -> {
                        coroutineScope.launch(ioDispatcher) {
                            try {
                                waterIntakeRepository.getHydrationIntakeForTheDay(1)
                                    .onEach {
                                        isLoading = false
                                        waterIntake = it
                                    }.launchIn(lifecycleOwner.lifecycleScope)
                                waterIntakeRepository.subscribeToRealtimeChannel()
                            } catch (e: Exception) {
                                isError = true
                            }
                        }
                    }

                    Lifecycle.Event.ON_STOP -> {
                        coroutineScope.launch(ioDispatcher) {
                            try {
                                waterIntakeRepository.unsubscribeRealtimeChannel()
                            } catch (e: Exception) {
                                isError = true
                            }
                        }
                    }

                    else -> Unit
                }
            }
            lifecycleOwner.lifecycle.addObserver(observer)
            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        }

        return SummaryState(
            isError = isError,
            hydrationGoal = waterIntake?.userGoal,
            hydrationToday = waterIntake?.totalIntake,
            isLoading = isLoading,
            loadingAmount = loadingAmount
        ) { event ->
            when (event) {
                is SummaryEvent.AddHydration -> {
                    isLoading = true
                    loadingAmount = event.hydration.amount
                    coroutineScope.launch(ioDispatcher) {
                        waterIntakeRepository.addHydrationIntake(event.hydration)
                    }
                }
            }
        }
    }
}