package com.cogito.water.data.di

import com.cogito.water.BuildConfig
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.PropertyConversionMethod
import io.github.jan.supabase.realtime.Realtime
import io.github.jan.supabase.realtime.RealtimeChannel
import io.github.jan.supabase.realtime.channel
import io.github.jan.supabase.realtime.realtime
import io.github.jan.supabase.serializer.MoshiSerializer
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO

internal class SupabaseProviderImpl: SupabaseProvider {
    lateinit var _realTimeChannel: RealtimeChannel
    override val realtimeChannel: RealtimeChannel
        get() = _realTimeChannel
    override val client: SupabaseClient = createSupabaseClient(
        supabaseUrl = BuildConfig.SUPABASE_URL,
        supabaseKey = BuildConfig.SUPABASE_KEY,
    ){
        defaultSerializer = MoshiSerializer()
        install(Realtime){
            serializer = MoshiSerializer()
        }
        install(Postgrest){
            propertyConversionMethod = PropertyConversionMethod.SERIAL_NAME
        }
    }.also {
        _realTimeChannel = it.realtime.channel("android")
    }

}