package com.gnamgpt.data

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader("x-goog-api-key", apiKey)
            .build()

        return chain.proceed(newRequest)
    }
}