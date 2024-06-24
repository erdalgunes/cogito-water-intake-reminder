package com.cogito.hydration.presentation.summary

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.cogito.hydration.data.model.DailyHydrationIntake
import com.cogito.hydration.data.repository.HydrationRepository
import com.cogito.hydration.presentation.summary.state.SummaryScreen
import com.slack.circuit.runtime.presenter.Presenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SummaryPresenter(private val waterIntakeRepository: HydrationRepository) :
    Presenter<SummaryScreen.State> {
    @Composable
    override fun present(): SummaryScreen.State {
        val lifecycleOwner = LocalLifecycleOwner.current
        val coroutineScope = rememberCoroutineScope()
        var waterIntake by remember { mutableStateOf<DailyHydrationIntake?>(null) }

        DisposableEffect(Unit) {
            val observer = LifecycleEventObserver { _, event ->
                when (event) {
                    Lifecycle.Event.ON_START -> {
                        coroutineScope.launch(Dispatchers.IO) {
                            waterIntakeRepository.getHydrationIntakeForTheDay(1)
                                .onEach {
                                    waterIntake = it
                                }.launchIn(lifecycleOwner.lifecycleScope)
                            waterIntakeRepository.subscribeToRealtimeChannel()
                        }
                    }

                    Lifecycle.Event.ON_STOP -> {
                        coroutineScope.launch(Dispatchers.IO) {
                            waterIntakeRepository.unsubscribeRealtimeChannel()
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

        return SummaryScreen.State(
            isError = waterIntake == null,
            hydrationGoal = waterIntake?.userGoal,
            hydrationToday = waterIntake?.totalIntake,
        )
    }
}