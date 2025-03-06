package com.gnamgpt.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import com.airbnb.lottie.LottieAnimationView
import com.gnamgpt.MainActivity
import com.gnamgpt.R

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val animationView = findViewById<LottieAnimationView>(R.id.lottieAnimationView)
        animationView.setAnimation(R.raw.splash_animation)
        animationView.repeatCount = 1
        animationView.playAnimation()

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 1500)
    }
}
