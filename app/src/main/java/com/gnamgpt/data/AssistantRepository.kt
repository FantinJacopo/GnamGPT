package com.gnamgpt.data

import android.util.Log
import com.gnamgpt.model.Content
import com.gnamgpt.model.GeminiRequest
import com.gnamgpt.model.Part
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.intellij.markdown.flavours.commonmark.CommonMarkFlavourDescriptor
import org.intellij.markdown.html.HtmlGenerator
import org.intellij.markdown.parser.MarkdownParser

class AssistantRepository(private val apiKey: String) {

    suspend fun getResponse(userMessage: String): String = withContext(Dispatchers.IO) {
        return@withContext try {
            val request = GeminiRequest(
                contents = listOf(
                    Content(
                        parts = listOf(
                            Part(text = userMessage)
                        )
                    )
                )
            )
            val gson = GsonBuilder().setPrettyPrinting().create()
            val requestJson = gson.toJson(request)
            Log.d("API_REQUEST_BODY", requestJson)
            val response = RetrofitClient.apiService.getAssistantResponse(apiKey, request)

            val src = response.candidates.firstOrNull()?.content?.parts?.firstOrNull()?.text ?: ""

            val flavour = CommonMarkFlavourDescriptor()
            val parsedTree = MarkdownParser(flavour).buildMarkdownTreeFromString(src)
            val html = HtmlGenerator(src, parsedTree, flavour).generateHtml()

            html
        } catch (e: Exception) {
            Log.e("API_ERROR", "Errore durante la chiamata API", e)
            "Mi dispiace, si è verificato un errore."
        }
    }
}