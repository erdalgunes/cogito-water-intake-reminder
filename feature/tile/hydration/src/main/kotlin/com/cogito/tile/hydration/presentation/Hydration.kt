package com.cogito.tile.hydration.presentation

import androidx.compose.runtime.Composable
import androidx.glance.GlanceComposable
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.LocalSize
import androidx.glance.background
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height

@Composable
@GlanceComposable
fun Hydration(modifier: GlanceModifier = GlanceModifier) {

}

@Composable
@GlanceComposable
fun HydrationContent(modifier: GlanceModifier = GlanceModifier) {

}

@Composable
@GlanceComposable
fun HydrationIndicator(
    modifier: GlanceModifier = GlanceModifier,
    goal: Int,
    today: Int,
) {
    val height = LocalSize.current.height
    val fillPercentage = if (goal != 0) today.toFloat() / goal.toFloat() else 0f
    val fillHeight = height - (height * fillPercentage)
    val emptyHeight = height - fillHeight
    Column(modifier = modifier.fillMaxSize()){
        Box(
            modifier = GlanceModifier.fillMaxWidth()
                .height(emptyHeight)
                .background(GlanceTheme.colors.background)
        ){}

        Box(
            modifier = GlanceModifier.fillMaxWidth()
                .height(fillHeight)
                .background(GlanceTheme.colors.primary)
        ) {}
    }
}