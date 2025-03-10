package com.gnamgpt.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import android.util.Log
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class UsersDatabase {
    // dark = true, light = false

    private val db = FirebaseFirestore.getInstance()
    private val favoriteMealsCollection = db.collection("favorite_meals")
    private val usersPreferencesCollection = db.collection("user_preferences")

    fun addRecipeToFavorites(recipeId: String) {
        val auth = FirebaseAuth.getInstance()
        val userId = auth.currentUser?.uid

        if (userId != null) {
            val favoriteId = "${userId}_$recipeId"  // ID unico combinando utente e ricetta

            val favoriteData = mapOf(
                "userId" to userId,
                "recipeId" to recipeId
            )

            favoriteMealsCollection.document(favoriteId).set(favoriteData)
                .addOnSuccessListener { println("Ricetta salvata tra i preferiti!") }
                .addOnFailureListener { e -> println("Errore: ${e.message}") }
        }
    }


    fun removeRecipeFromFavorites(recipeId: String) {
        val auth = FirebaseAuth.getInstance()
        val userId = auth.currentUser?.uid

        if (userId != null) {
            val favoriteId = "${userId}_$recipeId"

            favoriteMealsCollection.document(favoriteId).delete()
                .addOnSuccessListener { println("Ricetta rimossa dai preferiti!") }
                .addOnFailureListener { e -> println("Errore: ${e.message}") }
        }
    }

    fun saveUserPreferences(theme: Boolean, language: String) {
        val auth = FirebaseAuth.getInstance()
        val userId = auth.currentUser?.uid
        val t = if (theme) "dark" else "light"

        if (userId != null) {
            val userPreferences = mapOf(
                "theme" to t,
                "language" to language
            )

            usersPreferencesCollection.document(userId).set(userPreferences)
                .addOnSuccessListener { println("Preferenze salvate con successo!") }
                .addOnFailureListener { e -> println("Errore: ${e.message}") }
        }
    }

    fun getUserPreferences(callback: (String, String) -> Unit) {
        val auth = FirebaseAuth.getInstance()
        val userId = auth.currentUser?.uid

        if (userId != null) {
            usersPreferencesCollection.document(userId).get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        val theme = document.getString("theme") ?: "light"  // Default: chiaro
                        val language = document.getString("language") ?: "it"  // Default: italiano
                        callback(theme, language)
                    } else {
                        callback("light", "it")  // Valori di default se il documento non esiste
                    }
                }
                .addOnFailureListener { e ->
                    println("Errore: ${e.message}")
                    callback("light", "it")
                }
        } else {
            callback("light", "it")
        }
    }

    fun isRecipeFavorite(recipeId: String, callback: (Boolean) -> Unit) {
        val auth = FirebaseAuth.getInstance()
        val userId = auth.currentUser?.uid

        if (userId != null) {
            val favoriteId = "${userId}_$recipeId"

            favoriteMealsCollection.document(favoriteId).get()
                .addOnSuccessListener { document ->
                    callback(document.exists())
                }
                .addOnFailureListener {
                    callback(false)
                }
        } else {
            callback(false)
        }
    }

    suspend fun getUserFavorites(): List<String> {
        return suspendCoroutine { continuation ->
            val auth = FirebaseAuth.getInstance()
            val userId = auth.currentUser?.uid

            if (userId != null) {
                favoriteMealsCollection
                    .whereEqualTo("userId", userId)
                    .get()
                    .addOnSuccessListener { result ->
                        val recipeIds = mutableListOf<String>()
                        for (document in result) {
                            val recipeId = document.getString("recipeId")
                            if (recipeId != null) {
                                recipeIds.add(recipeId)
                            }
                        }
                        Log.d("UsersDatabase", "Extracted recipeIds: $recipeIds")
                        continuation.resume(recipeIds)  // Restituisce il risultato alla coroutine
                    }
                    .addOnFailureListener { e ->
                        Log.e("UsersDatabase", "Error fetching favorites: ${e.message}")
                        continuation.resume(emptyList())  // Restituisce una lista vuota in caso di errore
                    }
            } else {
                continuation.resume(emptyList())  // Restituisce una lista vuota se l'utente non è autenticato
            }
        }
    }

}