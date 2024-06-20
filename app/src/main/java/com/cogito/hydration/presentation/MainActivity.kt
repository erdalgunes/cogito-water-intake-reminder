package com.cogito.hydration.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
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

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        setTheme(android.R.style.Theme_DeviceDefault)

        setContent {
            WearApp()
        }
    }
}

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