package com.gnamgpt.data

import android.content.Context
import android.util.Log
import com.gnamgpt.model.GeminiRequest
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AssistantRepository(context: Context) {
    private val apiKey: String by lazy {
        val properties = context.assets.open("local.properties").bufferedReader().use { it.readText() }
        properties.lines().find { it.startsWith("GEMINI_API_KEY") }?.split("=")?.get(1) ?: ""
    }

    private val apiService: GeminiApiService = Retrofit.Builder()
        .baseUrl("https://generativelanguage.googleapis.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GeminiApiService::class.java)

    suspend fun getResponse(userMessage: String): String {
        return try {
            val request = GeminiRequest(userMessage)
            val response = apiService.getAssistantResponse("Bearer $apiKey", request)
            response.choices.firstOrNull()?.text ?: "Risposta non disponibile."
        } catch (e: Exception) {
            Log.e("API_ERROR", "Errore durante la chiamata API", e)
            "Mi dispiace, si è verificato un errore."
        }

    }
}
