package com.gnamgpt.data

import com.gnamgpt.model.GeminiRequest
import com.gnamgpt.model.GeminiResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface GeminiApiService {
    @POST("gemini-2.0-flash:generateContent")
    suspend fun getAssistantResponse(
        @Header("Authorization") apiKey: String,
        @Body request: GeminiRequest
    ): GeminiResponse
}