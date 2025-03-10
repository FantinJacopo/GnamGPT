package com.gnamgpt

import android.app.Application
import com.google.firebase.FirebaseApp
import com.gnamgpt.viewmodel.UserViewModel

class GnamGPTApp : Application() {
    // Istanza singleton del UserViewModel
    val userViewModel by lazy { UserViewModel() }

    companion object {
        private lateinit var instance: GnamGPTApp

        fun getInstance(): GnamGPTApp {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        // Memorizza l'istanza
        instance = this
        // Inizializza Firebase
        FirebaseApp.initializeApp(this)
    }
}