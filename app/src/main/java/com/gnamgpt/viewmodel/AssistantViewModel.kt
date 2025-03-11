package com.gnamgpt.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gnamgpt.data.AssistantRepository
import com.gnamgpt.model.Content
import com.gnamgpt.model.GeminiRequest
import com.gnamgpt.model.Message
import com.gnamgpt.model.Part
import com.google.gson.GsonBuilder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AssistantViewModel(private val apiKey: String) : ViewModel() {
    private val _messages = MutableStateFlow(
        listOf(Message("Ciao, sono GnamGPT! 😊\n" +
            "Cosa vuoi cucinare oggi? 🍳", false)) )
    val messages: StateFlow<List<Message>> = _messages

    private val assistantRepository = AssistantRepository(apiKey)

    private fun createGeminiRequest(userMessage: String): GeminiRequest {
        val systemPrompt = "Sei un assistente AI specializzato in ricette di cucina, ti chiami GnamGPT." +
                "Rispondi sempre in modo conciso e fornisci istruzioni chiare." +
                "Aggiungi emoji che aiutino a rendere la conversazione più interattiva." +
                "Rispondi SOLO a domande di cucina." +
                "Se una domanda non è di cucina, spiega all'utente che puoi rispondere solo a domande di cucina"


        val systemContent = Content(
            role = "system",
            parts = listOf(Part(text = systemPrompt))
        )

        val userContent = Content(
            parts = listOf(Part(text = userMessage))
        )

        return GeminiRequest(
            contents = listOf(systemContent, userContent)
        )
    }

    fun sendMessage(userInput: String, onAnimationEnd: () -> Unit) {
        viewModelScope.launch {
            try {
                _messages.value += Message(userInput, true)
                val request = createGeminiRequest(userInput)
                val gson = GsonBuilder().setPrettyPrinting().create()

                val response = assistantRepository.getResponse(gson.toJson(request))
                response.removeRange(response.length-2, response.length)
                _messages.value += Message(response, false)
            } catch (e: Exception) {
                _messages.value += Message("Errore", false)
            } finally {
                onAnimationEnd()
            }
        }
    }
}