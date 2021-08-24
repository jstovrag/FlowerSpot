package com.flower.remote.environment

import okhttp3.logging.HttpLoggingInterceptor

sealed class EnvironmentConfig : IEnvironmentConfig {

    object Development : EnvironmentConfig()
    object Production : EnvironmentConfig()

    override fun baseURL() =
        "${server()}/$currentServerVersion/"

    override fun logLevel() = when (this) {
        Production ->
            HttpLoggingInterceptor.Level.NONE
        Development ->
            HttpLoggingInterceptor.Level.BODY
    }

    private fun server() = when (this) {
        Production ->
            "https://flowrspot-api.herokuapp.com"
        Development ->
            "https://flowrspot-api.herokuapp.com"
    }

    private val currentServerVersion = "v1"
}
