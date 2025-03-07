package com.gnamgpt.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object NetworkModule {
    private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

    val api: TheMealDBService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TheMealDBService::class.java)
    }
}
