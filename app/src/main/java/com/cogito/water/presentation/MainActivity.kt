/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.cogito.water.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.wear.tooling.preview.devices.WearDevices
import appModule
import com.cogito.water.screen.state.SummaryScreen
import com.slack.circuit.foundation.Circuit
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
fun WearApp(circuit: Circuit = koinInject()) {
    KoinAndroidContext {
        CircuitCompositionLocals(circuit) {
            CircuitContent(SummaryScreen)
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