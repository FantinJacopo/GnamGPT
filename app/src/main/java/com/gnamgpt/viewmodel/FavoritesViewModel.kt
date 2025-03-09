package com.gnamgpt.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import com.gnamgpt.data.MealRepository
import com.gnamgpt.data.NetworkModule
import com.gnamgpt.data.UsersDatabase
import com.gnamgpt.model.Meal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavoritesViewModel : ViewModel() {
    private val mealRepository = MealRepository(NetworkModule.api)
    private val usersDatabase = UsersDatabase()

    private val _favoriteMeals = MutableStateFlow<List<Meal>?>(null)
    val favoriteMeals: StateFlow<List<Meal>?> = _favoriteMeals

    fun loadFavoriteMeals() {
        viewModelScope.launch {
            val favoriteRecipeIds = usersDatabase.getUserFavorites()

            if (favoriteRecipeIds.isNotEmpty()) {
                val mealDeferreds = favoriteRecipeIds.map { recipeId ->
                    async {
                        mealRepository.getMealById(recipeId)?.let { recipe ->
                            Meal(
                                idMeal = recipe.idMeal,
                                strMeal = recipe.strMeal ?: "N/A",
                                strCategory = recipe.strCategory,
                                strArea = recipe.strArea,
                                strInstructions = recipe.strInstructions,
                                strMealThumb = recipe.strMealThumb
                            )
                        }
                    }
                }
                val meals = mealDeferreds.awaitAll().filterNotNull()
                _favoriteMeals.value = meals
            } else {
                _favoriteMeals.value = emptyList()
            }
        }
    }
}