package com.gnamgpt.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.gnamgpt.data.MealRepository
import com.gnamgpt.data.NetworkModule
import com.gnamgpt.data.UsersDatabase
import com.gnamgpt.model.Meal
import com.gnamgpt.model.Category
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.gnamgpt.model.Recipe

class MealViewModel : ViewModel() {

    private val repository = MealRepository(NetworkModule.api)

    private val _meals = MutableStateFlow<List<Meal>?>(null)
    val meals: StateFlow<List<Meal>?> = _meals

    // Stato per le categorie
    private val _categories = MutableStateFlow<List<Category>?>(null)
    val categories: StateFlow<List<Category>?> = _categories
    
    // Stato per i piatti di una categoria
    private val _categoryMeals = MutableStateFlow<List<Meal>?>(null)
    val categoryMeals: StateFlow<List<Meal>?> = _categoryMeals

    // Stato per i piatti in home
    private val _homeCategoryMeals = MutableStateFlow<Map<String, List<Meal>>>(emptyMap())
    val homeCategoryMeals: StateFlow<Map<String, List<Meal>>> = _homeCategoryMeals

    private val _mealDetail = MutableStateFlow<Recipe?>(null)
    val mealDetail: StateFlow<Recipe?> = _mealDetail

    val usersDatabase = UsersDatabase()

    init {
        loadCategories(5)
    }

    fun loadMealDetail(mealId: String) {
        viewModelScope.launch {
            val response = repository.getMealById(mealId)
            _mealDetail.value = response
        }
    }

    fun searchMeal(name: String) {
        viewModelScope.launch {
            _meals.value = repository.searchMealByName(name)?.meals
            Log.d("MealViewModel", "Numero di pasti trovati: ${meals.value?.size ?: 0}")
        }
    }

    fun getRandomMeal() {
        viewModelScope.launch {
            _meals.value = repository.getRandomMeal()?.meals
        }
    }
    
    // Funzione per caricare le categorie
    fun loadCategories(num: Int? = null) {
        viewModelScope.launch {
            val categoriesResponse = repository.getAllCategories()
            val categoriesList = categoriesResponse?.categories?.take(num ?: Int.MAX_VALUE)

            _categories.value = categoriesList

            val mealsByCategory = mutableMapOf<String, List<Meal>>()
            categoriesList?.forEach { category ->
                val meals = repository.getMealsByCategory(category.strCategory)?.meals?.take(7)
                if (meals != null) {
                    mealsByCategory[category.strCategory] = meals
                }
            }
            _homeCategoryMeals.value = mealsByCategory
        }
    }

    
    // Funzione per caricare i pasti di una categoria specifica
    private fun loadMealsByCategory(category: String, num: Int) {
        viewModelScope.launch {
            val mealsInCategory = repository.getMealsByCategory(category)?.meals
            _categoryMeals.value = mealsInCategory?.take(num)
        }
    }
    
    // Funzione per caricare *tutti* i pasti di una categoria
    fun loadAllMealsByCategory(category: String) {
        viewModelScope.launch {
            _categoryMeals.value = repository.getMealsByCategory(category)?.meals
        }
    }
}
