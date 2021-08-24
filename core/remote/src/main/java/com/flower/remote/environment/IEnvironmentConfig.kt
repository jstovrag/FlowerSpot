package com.flower.remote.environment

import okhttp3.logging.HttpLoggingInterceptor

interface IEnvironmentConfig {
    fun baseURL(): String
    fun logLevel(): HttpLoggingInterceptor.Level
}
