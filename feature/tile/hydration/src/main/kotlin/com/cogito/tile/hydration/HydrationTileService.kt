package com.cogito.tile.hydration

import androidx.compose.runtime.Composable
import androidx.glance.wear.tiles.GlanceTileService
import com.cogito.tile.hydration.presentation.HydrationIndicator

class HydrationTileService: GlanceTileService() {
    @Composable
    override fun Content() {
        HydrationIndicator(
            goal = 1000,
            today = 500,
        )
    }
}