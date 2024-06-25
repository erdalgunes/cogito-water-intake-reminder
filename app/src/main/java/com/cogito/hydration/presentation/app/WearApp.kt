package com.cogito.hydration.presentation.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.tooling.preview.devices.WearDevices
import appModule
import com.cogito.hydration.di.CircuitProvider
import com.cogito.hydration.presentation.summary.state.SummaryScreen
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.CircuitContent
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun WearApp(circuitProvider: CircuitProvider = koinInject()) {
    KoinAndroidContext {
        CircuitCompositionLocals(circuitProvider.circuit) {
            CircuitContent(SummaryScreen, modifier = Modifier.fillMaxSize())
        }
    }
}

@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    KoinApplication(application = {
        modules(appModule)
    }) {
        WearApp()
    }
}