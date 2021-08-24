package com.flower.remote.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

const val CONTENT_TYPE = "Content-Type"

class RequestHeadersInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        request.addHeader(CONTENT_TYPE, "application/json")
        return chain.proceed(request.build())
    }
}
