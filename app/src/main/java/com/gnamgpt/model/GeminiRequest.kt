package com.gnamgpt.model

data class GeminiRequest(
    val contents: List<Content>
)

data class Content(
    val role: String? = null,
    val parts: List<Part>
)

data class Part(
    val text: String
)