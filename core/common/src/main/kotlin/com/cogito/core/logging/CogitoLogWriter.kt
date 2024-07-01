package com.cogito.core.logging

import android.util.Log
import co.touchlab.kermit.CommonWriter
import co.touchlab.kermit.DefaultFormatter
import co.touchlab.kermit.LogWriter
import co.touchlab.kermit.Message
import co.touchlab.kermit.Severity

class CogitoLogWriter(private val isProduction: Boolean) : LogWriter() {

    // When running unit tests, Log calls will fail. Back up to a common writer
    private val testWriter: CommonWriter = CommonWriter(DefaultFormatter)

    override fun log(severity: Severity, message: String, tag: String, throwable: Throwable?) {
        if (!isLoggable(tag, severity)) return
        val formattedMessage = DefaultFormatter.formatMessage(null, null, Message(message))
        try {
            if (throwable == null) {
                when (severity) {
                    Severity.Verbose -> Log.v(tag, formattedMessage)
                    Severity.Debug -> Log.d(tag, formattedMessage)
                    Severity.Info -> Log.i(tag, formattedMessage)
                    Severity.Warn -> Log.w(tag, formattedMessage)
                    Severity.Error -> Log.e(tag, formattedMessage)
                    Severity.Assert -> Log.wtf(tag, formattedMessage)
                }
            } else {
                when (severity) {
                    Severity.Verbose -> Log.v(tag, formattedMessage, throwable)
                    Severity.Debug -> Log.d(tag, formattedMessage, throwable)
                    Severity.Info -> Log.i(tag, formattedMessage, throwable)
                    Severity.Warn -> Log.w(tag, formattedMessage, throwable)
                    Severity.Error -> Log.e(tag, formattedMessage, throwable)
                    Severity.Assert -> Log.wtf(tag, formattedMessage, throwable)
                }
            }
        } catch (e: Exception) {
            testWriter.log(severity, message, tag, throwable)
        }
    }

    override fun isLoggable(tag: String, severity: Severity): Boolean = !isProduction
}