package com.cogito.core.concurrency

sealed interface CogitoDispatchers{
    data object Default: CogitoDispatchers
    data object IO: CogitoDispatchers
}