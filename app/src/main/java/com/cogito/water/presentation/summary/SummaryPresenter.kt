package com.cogito.water.presentation.summary

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.cogito.water.data.model.WaterIntake
import com.cogito.water.data.repository.WaterIntakeRepository
import com.cogito.water.presentation.summary.state.SummaryScreen
import com.slack.circuit.runtime.presenter.Presenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext

class SummaryPresenter(private val waterIntakeRepository: WaterIntakeRepository) :
    Presenter<SummaryScreen.State> {
    @Composable
    override fun present(): SummaryScreen.State {
        var waterIntake by remember { mutableStateOf<List<WaterIntake>>(listOf()) }
        var totalWaterIntake by remember { mutableStateOf(0) }
        LaunchedEffect(Unit) {
            withContext(Dispatchers.IO) {
                waterIntakeRepository.getWaterIntake(1)
                    .onEach {
                        waterIntake = it
                        totalWaterIntake = it.sumOf { intake -> intake.amount }
                    }.launchIn(this)
            }
        }
        return SummaryScreen.State(
            isError = waterIntake.isEmpty(),
            waterGoal = 1000,
            waterIntake = totalWaterIntake,
        )
    }
}