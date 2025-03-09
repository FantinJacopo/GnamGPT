package com.gnamgpt.data

import com.gnamgpt.model.*
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMealDBService {

    @GET("search.php")
    suspend fun searchMealByName(@Query("s") name: String): MealResponse

    @GET("lookup.php")
    suspend fun getMealById(@Query("i") id: String): RecipeResponse

    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") category: String): MealResponse

    @GET("filter.php")
    suspend fun getMealsByArea(@Query("a") area: String): MealResponse

    @GET("categories.php")
    suspend fun getAllCategories(): CategoryResponse

    @GET("list.php?a=list")
    suspend fun getAllAreas(): AreaResponse

    @GET("list.php?i=list")
    suspend fun getAllIngredients(): IngredientResponse

    @GET("filter.php")
    suspend fun getMealsByMainIngredient(@Query("i") ingredient: String): MealResponse

    @GET("random.php")
    suspend fun getRandomMeal(): MealResponse
}