package com.gnamgpt.data

import com.gnamgpt.model.MealResponse
import android.util.Log
import com.gnamgpt.model.AreaResponse
import com.gnamgpt.model.CategoryResponse
import com.gnamgpt.model.Meal
import com.gnamgpt.model.Recipe

class MealRepository(private val api: TheMealDBService) {

    suspend fun searchMealByName(name: String): MealResponse? {
        return try {
            api.searchMealByName(name)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getMealById(id: String): Recipe? {
        return try {
            Log.d("MealRepository", "Tentativo di ottenere il piatto con ID: $id")
            val recipe = api.getMealById(id)
            Log.d("Ritorno api", recipe.toString())
            return recipe.meals?.firstOrNull()
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getMealsByCategory(category: String): MealResponse? {
        return try {
            val response = api.getMealsByCategory(category)
            Log.d("MealRepository", "Numero di pasti ricevuti per la categoria $category: ${response?.meals?.size ?: 0}")
            response
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getAllCategories(): CategoryResponse? {
        return try {
            api.getAllCategories()
        } catch (e: Exception) {
            Log.e("MealRepository", "Errore in getAllCategories()", e)
            null
        }
    }

    suspend fun getAllAreas(): AreaResponse?{
        return try {
            api.getAllAreas()
        } catch (e: Exception) {
            Log.e("MealRepository", "Errore in getAllAreas()", e)
            null
        }
    }

    suspend fun getMealsByMainIngredient(ingredient: String): MealResponse? {
        return try {
            api.getMealsByMainIngredient(ingredient)
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