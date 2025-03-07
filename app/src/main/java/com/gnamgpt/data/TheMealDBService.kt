package com.gnamgpt.data

import com.gnamgpt.model.MealResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMealDBService {

    @GET("search.php")
    suspend fun searchMealByName(@Query("s") name: String): MealResponse

    @GET("lookup.php")
    suspend fun getMealById(@Query("i") id: String): MealResponse

    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") category: String): MealResponse

    @GET("categories.php")
    suspend fun getAllCategories(): MealResponse

    @GET("filter.php")
    suspend fun getMealsByIngredient(@Query("i") ingredient: String): MealResponse

    @GET("random.php")
    suspend fun getRandomMeal(): MealResponse
}