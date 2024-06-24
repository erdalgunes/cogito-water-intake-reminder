package com.cogito.core.network

sealed interface CogitoDispatchers{
    data object Default: CogitoDispatchers
    data object IO: CogitoDispatchers
}