package com.cogito.water.presentation.summary

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.Text
import androidx.wear.tooling.preview.devices.WearDevices
import com.cogito.water.presentation.theme.WaterIntakeReminderTheme
import com.cogito.water.presentation.summary.state.SummaryScreen

@Composable
fun Summary(state: SummaryScreen.State, modifier: Modifier) {
    val text = when(state.isError){
        true -> "No Water Intake"
        false -> "Water Intake: ${state.waterIntake} ml"
    }
    Box(modifier, contentAlignment = Alignment.Center) {
        Text(text = text)
    }
}

@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
fun SummaryPreview() {
    WaterIntakeReminderTheme {
        Summary(SummaryScreen.State(false, 300, 1000), Modifier.fillMaxSize())
    }
}