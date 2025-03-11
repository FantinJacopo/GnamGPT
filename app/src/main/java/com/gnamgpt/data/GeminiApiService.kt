package com.gnamgpt.data

import com.gnamgpt.model.GeminiRequest
import com.gnamgpt.model.GeminiResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface GeminiApiService {
    @Headers("Content-Type: application/json"/*, "x-goog-api-key: INSERIRE API KEY"*/)
    @POST("/v1beta/models/gemini-2.0-flash:generateContent")
    suspend fun getAssistantResponse(
        @Header("x-goog-api-key") apiKey: String,
        @Body request: GeminiRequest,
    ): GeminiResponse
}