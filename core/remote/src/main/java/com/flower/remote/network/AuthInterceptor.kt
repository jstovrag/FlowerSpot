package com.flower.remote.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        // TODO: Add token
        request.addHeader("Authorization", "Bearer ${"token"}")
        return chain.proceed(request.build())
    }
}
