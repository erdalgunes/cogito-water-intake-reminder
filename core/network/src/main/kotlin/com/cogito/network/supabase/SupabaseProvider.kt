package com.cogito.network.supabase

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.realtime.RealtimeChannel

interface SupabaseProvider {
    val client: SupabaseClient
}