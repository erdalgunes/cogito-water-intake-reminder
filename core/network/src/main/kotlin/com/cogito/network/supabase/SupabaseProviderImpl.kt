package com.cogito.network.supabase

import com.cogito.core.concurrency.CogitoDispatchers
import com.cogito.network.BuildConfig
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.FlowType
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.serializer.MoshiSerializer
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.core.qualifier.named
import org.koin.mp.KoinPlatform.getKoin

internal class SupabaseProviderImpl : SupabaseProvider {
    private val ioDispatcher: CoroutineDispatcher = getKoin().get(named<CogitoDispatchers.IO>())

    override val client: SupabaseClient = createSupabaseClient(
        supabaseUrl = BuildConfig.SUPABASE_URL,
        supabaseKey = BuildConfig.SUPABASE_KEY,
    ){
        defaultSerializer = MoshiSerializer()
        install(Postgrest)
        install(Auth){
            flowType = FlowType.IMPLICIT
            coroutineDispatcher = ioDispatcher
        }
    }
}