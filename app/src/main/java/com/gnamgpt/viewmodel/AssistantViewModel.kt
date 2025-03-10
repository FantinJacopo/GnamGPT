package com.gnamgpt.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gnamgpt.data.AssistantRepository
import com.gnamgpt.model.Message
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AssistantViewModel : ViewModel() {
    private val _messages = MutableStateFlow<List<Message>>(
        listOf(Message("Ciao! Chiedimi qualsiasi cosa sul cibo 🍕🍔🍝", isFromUser = false))
    )
    val messages: StateFlow<List<Message>> = _messages

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun sendMessage(userInput: String) {
        val currentMessages = _messages.value.toMutableList()
        currentMessages.add(Message(userInput, isFromUser = true))
        _messages.value = currentMessages

        _isLoading.value = true

        viewModelScope.launch {
            val assistantRepository = AssistantRepository(Application())
            val response = assistantRepository.getResponse(userInput)
            _isLoading.value = false
            _messages.value = currentMessages + Message(response, isFromUser = false)
        }
    }
}