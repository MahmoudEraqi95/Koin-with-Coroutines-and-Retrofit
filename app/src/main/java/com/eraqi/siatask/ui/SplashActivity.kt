package com.eraqi.siatask.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.eraqi.siatask.R
/**
* the first screen that appears to the user, it uses a handler to schedule starting the main activity after a specfic amount of time
 */
class SplashActivity:AppCompatActivity() {
    private val SPLASH_TIME_OUT:Long = 3000 // 3 sec
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({

            startActivity(Intent(this,MainActivity::class.java))
            finish()

        }, SPLASH_TIME_OUT)
    }


}