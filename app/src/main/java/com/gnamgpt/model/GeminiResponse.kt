package com.gnamgpt.model

data class GeminiResponse(
    val choices: List<Choice>
)

data class Choice(
    val text: String
)
