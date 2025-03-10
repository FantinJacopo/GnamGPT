package com.gnamgpt.data

import com.gnamgpt.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://generativelanguage.googleapis.com/v1beta/models/"

    private const val apiKey: String = BuildConfig.API_KEY // Recupero della chiave API dal build.gradle


    private val client = OkHttpClient.Builder()
        .addInterceptor(ApiKeyInterceptor(apiKey))  // Aggiunge l'Interceptor con la chiave API
        .build()

    val apiService: GeminiApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GeminiApiService::class.java)
}
