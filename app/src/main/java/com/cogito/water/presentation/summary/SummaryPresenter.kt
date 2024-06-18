package com.cogito.water.presentation.summary

import androidx.compose.runtime.Composable
import com.cogito.water.screen.state.SummaryScreen
import com.slack.circuit.runtime.presenter.Presenter

class SummaryPresenter: Presenter<SummaryScreen.State> {
    @Composable
    override fun present(): SummaryScreen.State {
        return SummaryScreen.State(300, 1000)
    }
}