package com.gnamgpt.model

data class Ingredient(
    val idIngredient: String,
    val strIngredient: String,
    val strDescription: String,
    val strType: String
)

data class IngredientResponse(
    val ingredients: List<Ingredient>?
)
