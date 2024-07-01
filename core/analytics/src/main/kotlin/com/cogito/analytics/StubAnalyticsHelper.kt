package com.cogito.analytics

import android.util.Log
import co.touchlab.kermit.Logger
import org.koin.core.annotation.Singleton
import org.koin.core.parameter.parametersOf
import org.koin.mp.KoinPlatform.getKoin

private const val TAG = "StubAnalyticsHelper"

/**
 * An implementation of AnalyticsHelper just writes the events to logcat. Used in builds where no
 * analytics events should be sent to a backend.
 */
@Singleton
internal class StubAnalyticsHelper(private val log: Logger) : AnalyticsHelper {

    override fun logEvent(event: AnalyticsEvent) {
        log.d("Received analytics event: $event")
    }
}