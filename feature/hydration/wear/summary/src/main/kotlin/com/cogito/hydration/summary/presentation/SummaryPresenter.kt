package com.cogito.hydration.summary.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.cogito.hydration.summary.presentation.state.SummaryEvent
import com.cogito.hydration.summary.presentation.state.SummaryState
import com.cogito.model.ui.HydrationSummary
import com.slack.circuit.runtime.presenter.Presenter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.future.await
import kotlinx.coroutines.future.future
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import co.touchlab.kermit.Logger
import com.cogito.core.concurrency.CogitoDispatchers
import com.cogito.domain.AddHydrationUseCase
import com.cogito.domain.AuthenticateUserUseCase
import com.cogito.domain.GetHydrationSummaryForTodayUseCase
import com.cogito.domain.GetUserHydrationGoalUseCase
import com.cogito.domain.SyncUserHydrationGoalUseCase

class SummaryPresenter(
    private val authenticateUserUseCase: AuthenticateUserUseCase,
    private val getHydrationGoalUseCase: GetUserHydrationGoalUseCase,
    private val syncUserHydrationGoalUseCase: SyncUserHydrationGoalUseCase,
    private val getHydrationSummaryForTodayUseCase: GetHydrationSummaryForTodayUseCase,
    private val addHydrationUseCase: AddHydrationUseCase,
) : Presenter<SummaryState> {
    @Composable
    override fun present(): SummaryState {
        val log: Logger = koinInject<Logger>(parameters = { parametersOf("SummaryPresenter") })
        val ioDispatcher: CoroutineDispatcher = koinInject(named<CogitoDispatchers.IO>())
        val coroutineScope = rememberCoroutineScope()
        var hydrationSummary by remember { mutableStateOf<HydrationSummary?>(null) }
        var isLoading by remember { mutableStateOf(true) }
        var loadingAmount by remember { mutableIntStateOf(500) }
        var isError by remember { mutableStateOf(false) }

        LaunchedEffect(isError) {
            coroutineScope.launch(ioDispatcher) {
                future { authenticateUserUseCase() }.await()
                val userHydrationGoal = getHydrationGoalUseCase()
                syncUserHydrationGoalUseCase()
                try {
                    getHydrationSummaryForTodayUseCase().onEach { summary ->
                        isLoading = true
                        hydrationSummary = HydrationSummary(
                            summary,
                            userHydrationGoal,
                        )
                        isLoading = false
                    }.launchIn(this)
                } catch (e: Exception){
                    log.e("Error fetching hydration summary", e)
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
                        addHydrationUseCase(event.hydration)
                    }
                }
            }
        }
    }
}