package com.gnamgpt

import android.app.Application
import com.google.firebase.FirebaseApp

class GnamGPTApp : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}
