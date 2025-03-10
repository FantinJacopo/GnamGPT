package com.gnamgpt.model

data class GeminiRequest(
    val prompt: String,
    val responseFormat: String = "json"
)