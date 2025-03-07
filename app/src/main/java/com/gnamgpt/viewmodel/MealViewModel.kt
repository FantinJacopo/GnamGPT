package com.gnamgpt.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gnamgpt.data.MealRepository
import com.gnamgpt.data.NetworkModule
import com.gnamgpt.model.Meal
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MealViewModel : ViewModel() {

    private val repository = MealRepository(NetworkModule.api)

    private val _meals = MutableStateFlow<List<Meal>?>(null)
    val meals: StateFlow<List<Meal>?> = _meals

    fun searchMeal(name: String) {
        viewModelScope.launch {
            _meals.value = repository.searchMealByName(name)?.meals
        }
    }

    fun getRandomMeal() {
        viewModelScope.launch {
            _meals.value = repository.getRandomMeal()?.meals
        }
    }
}
