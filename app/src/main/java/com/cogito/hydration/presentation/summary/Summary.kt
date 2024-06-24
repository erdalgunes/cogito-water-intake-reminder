package com.cogito.hydration.presentation.summary

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Text
import androidx.wear.tooling.preview.devices.WearDevices
import com.cogito.core.designsystem.theme.CogitoTheme
import com.cogito.hydration.data.model.HydrationIntake
import com.cogito.hydration.data.repository.HydrationRepository
import com.cogito.hydration.presentation.summary.state.SummaryScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun Summary(state: SummaryScreen.State, modifier: Modifier, hydrationRepository: HydrationRepository? = null) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    CogitoTheme {
        SummaryContent(state, modifier){ amount ->
            scope.launch(Dispatchers.IO) {
                hydrationRepository?.addHydrationIntake(
                    HydrationIntake(
                        amount = amount,
                        userId = 1,
                    )
                )
            }
            Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show()
        }
    }
}

@Composable
fun SummaryContent(state: SummaryScreen.State, modifier: Modifier, onItemClick: (Int) -> Unit = {}){
    val configuration = LocalConfiguration.current
    val text = when (state.isError) {
        true -> "You didn't drink today"
        false -> "${state.hydrationToday} ml"
    }
    Box(
        modifier.background(color = MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        WaterIntakeSummary(
            today = state.hydrationToday ?: 0,
            goal = state.hydrationGoal ?: 0,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        )
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(bottom = 32.dp)
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = (configuration.screenWidthDp.dp - 48.dp) / 2)
        ) {
            items(state.drinks.size) { index ->
                Column(
                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = MaterialTheme.shapes.small,
                        )
                        .clickable {
                            onItemClick(state.drinks[index].amount)
                        }
                    ,
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    val drink = state.drinks[index]
                    Image(
                        modifier = Modifier,
                        painter = painterResource(id = drink.icon),
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimaryContainer),
                        contentDescription = null,
                    )
                    Text(
                        modifier = Modifier.padding(top = 4.dp),
                        text = state.drinks[index].amount.toString(),
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                }
            }
        }
    }

}

@Composable
fun WaterIntakeSummary(today: Int, goal: Int, modifier: Modifier) {
    val color = MaterialTheme.colorScheme.primary
    val fillPercentage = if (goal != 0) today.toFloat() / goal.toFloat() else 0f
    Box(modifier = modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val fillSize = size.height * fillPercentage
            drawRect(
                color = color,
                topLeft = Offset(x = 0f, y = size.height - fillSize),
                size = Size(
                    width = size.width,
                    height = size.height * fillPercentage
                ),
                style = Fill
            )
        }
    }
}

@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
fun SummaryPreview() {
    CogitoTheme {
        SummaryContent(
            state = SummaryScreen.State(
                hydrationToday = 500,
                hydrationGoal = 2000
            ),
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SummaryPreviewDark() {
    CogitoTheme {
        SummaryContent(
            state = SummaryScreen.State(
                hydrationToday = 500,
                hydrationGoal = 2000
            ),
            modifier = Modifier.fillMaxSize()
        )
    }
}