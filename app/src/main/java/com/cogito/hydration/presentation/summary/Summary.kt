package com.cogito.hydration.presentation.summary

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Text
import androidx.wear.tooling.preview.devices.WearDevices
import com.cogito.core.designsystem.theme.CogitoAndroidWearTheme
import com.cogito.hydration.data.model.HydrationIntake
import com.cogito.hydration.presentation.summary.state.SummaryEvent
import com.cogito.hydration.presentation.summary.state.SummaryState
import kotlin.math.PI
import kotlin.math.sin

@Composable
fun Summary(
    state: SummaryState,
    modifier: Modifier,
) {
    CogitoAndroidWearTheme {
        SummaryContent(state, modifier)
    }
}

@Composable
fun SummaryContent(
    state: SummaryState,
    modifier: Modifier,
) {
    val configuration = LocalConfiguration.current
    val text = when (state.isError) {
        true -> "You didn't drink today"
        false -> "${state.hydrationToday} ml"
    }
    Box(
        modifier.background(color = MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        HydrationSummary(
            today = state.hydrationToday ?: 0,
            goal = state.hydrationGoal ?: 0,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            isLoading = state.isLoading,
            loadingAmount = state.loadingAmount ?: 0,
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
                            state.eventSink(
                                SummaryEvent.AddHydration(
                                HydrationIntake(
                                    userId = 1,
                                    amount = state.drinks[index].amount,
                                )
                            ))
                        },
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
fun AnimatedWave(
    modifier: Modifier = Modifier,
    color: Color,
    frequency: Float,
    animationFillPercentage: Float,
) {
    val phase = remember { Animatable(0f) }
    val height = remember { mutableFloatStateOf(0f) }

    LaunchedEffect(Unit) {
        phase.animateTo(
            targetValue = 2 * PI.toFloat(),
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1500, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            )
        )
    }

    Canvas(modifier = modifier.onSizeChanged { height.floatValue = it.height.toFloat() }) {
        val path = Path()
        val animationFillSize = size.height * animationFillPercentage
        val startY = size.height - animationFillSize

        path.moveTo(0f, startY)
        for (x in 0..size.width.toInt()) {
            val dx = x / size.width
            val dy = sin(dx * frequency * 2 * PI.toFloat() + phase.value) * 15f
            path.lineTo(dx * size.width, startY + dy)
        }

        path.lineTo(size.width, size.height)
        path.lineTo(0f, size.height)
        path.close()

        drawPath(path, color)
    }
}

@Composable
fun HydrationSummary(
    modifier: Modifier,
    today: Int,
    goal: Int,
    isLoading: Boolean = false,
    loadingAmount: Int = 0
) {
    val color = MaterialTheme.colorScheme.primary
    val fillPercentage = if (goal != 0) today.toFloat() / goal.toFloat() else 0f
    val animationFillPercentage =
        if (goal != 0) (today.toFloat() + loadingAmount.toFloat()) / goal.toFloat() else 0f
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
        if (isLoading) {
            AnimatedWave(
                modifier = Modifier.fillMaxSize(),
                color = color,
                frequency = 1f,
                animationFillPercentage = animationFillPercentage,
            )
        }
    }
}

@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
fun SummaryPreview() {
    CogitoAndroidWearTheme {
        SummaryContent(
            state = SummaryState(
                hydrationToday = 500,
                hydrationGoal = 2000
            ){},
            modifier = Modifier.fillMaxSize()
        )
    }
}