package com.gnamgpt.data

import com.gnamgpt.model.MealResponse

class MealRepository(private val api: TheMealDBService) {

    suspend fun searchMealByName(name: String): MealResponse? {
        return try {
            api.searchMealByName(name)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getMealById(id: String): MealResponse? {
        return try {
            api.getMealById(id)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getMealsByCategory(category: String): MealResponse? {
        return try {
            api.getMealsByCategory(category)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getAllCategories(): MealResponse? {
        return try {
            api.getAllCategories()
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getMealsByIngredient(ingredient: String): MealResponse? {
        return try {
            api.getMealsByIngredient(ingredient)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getRandomMeal(): MealResponse? {
        return try {
            api.getRandomMeal()
        } catch (e: Exception) {
            null
        }
    }
}