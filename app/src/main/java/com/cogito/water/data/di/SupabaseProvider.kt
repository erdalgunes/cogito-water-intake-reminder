package com.cogito.water.data.di

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.realtime.RealtimeChannel

interface SupabaseProvider {
    val client: SupabaseClient
    val realtimeChannel: RealtimeChannel
}