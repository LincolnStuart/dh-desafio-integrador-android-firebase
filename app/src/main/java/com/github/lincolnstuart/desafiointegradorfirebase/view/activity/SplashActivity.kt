package com.github.lincolnstuart.desafiointegradorfirebase.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModelProvider
import com.github.lincolnstuart.desafiointegradorfirebase.databinding.ActivitySplashBinding
import com.github.lincolnstuart.desafiointegradorfirebase.util.extension.Constants.SPLASH_TIMEOUT
import com.github.lincolnstuart.desafiointegradorfirebase.viewmodel.SplashViewModel

class SplashActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySplashBinding
    private lateinit var viewmodel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewmodel = ViewModelProvider(this).get(SplashViewModel::class.java)
        initSplashTimer()
    }

    private fun initSplashTimer() {
        Handler(Looper.getMainLooper()).postDelayed({
            defineDestination()
            finish()
        }, SPLASH_TIMEOUT)
    }

    private fun defineDestination() {
        if (viewmodel.isAuthenticated()) {
            startActivity(Intent(this, GameListActivity::class.java))
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}