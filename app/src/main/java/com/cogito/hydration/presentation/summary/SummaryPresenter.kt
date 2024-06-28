package com.cogito.hydration.presentation.summary

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
import com.cogito.data.repository.HydrationRepository
import com.cogito.data.repository.UserRepository
import com.cogito.hydration.presentation.summary.state.SummaryEvent
import com.cogito.hydration.presentation.summary.state.SummaryState
import com.cogito.model.data.UserDataModel
import com.cogito.model.ui.HydrationSummary
import com.slack.circuit.runtime.presenter.Presenter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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

        LaunchedEffect(Unit) {
            val userDataModel = UserDataModel(
                name = "Erdal",
                email = "erdalgns@gmail.com",
                goal = 2500,
            )
            coroutineScope.launch(ioDispatcher) {
                try {
                    userId = userRepository.getUserId(userDataModel.email)
                    Log.d("SummaryPresenter", "present: $userId")
                    hydrationRepository.getHydrationSummaryToday(userId).onEach { summary ->
                        Log.d("SummaryPresenter", "present: $summary")
                        isLoading = true
                        hydrationSummary = HydrationSummary(
                            summary,
                            2500,
                        )
                        isLoading = false
                    }.launchIn(this)
                } catch (e: Exception) {
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
                    loadingAmount = event.hydration.amount
                    coroutineScope.launch(ioDispatcher) {
                        hydrationRepository.addHydrationIntake(
                            userId = userId,
                            event.hydration
                        )
                    }
                }
            }
        }
    }
}