package com.syhong.eridug.plugin

interface HealthCheckPlugin {
    suspend fun health() : String
}