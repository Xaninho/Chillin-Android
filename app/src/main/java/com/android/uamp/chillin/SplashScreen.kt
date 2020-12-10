package com.android.uamp.chillin

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SplashScreen : AppCompatActivity() {

    private var prefs: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = getSharedPreferences("com.android.uamp.chillin", MODE_PRIVATE)
    }

    override fun onResume() {
        super.onResume()

        if (prefs!!.getBoolean("firstrun", true)) {

            val intent = Intent(this, OnboardingActivity::class.java)
            startActivity(intent)
            finish()

            prefs!!.edit().putBoolean("firstrun", false).apply()

        } else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}