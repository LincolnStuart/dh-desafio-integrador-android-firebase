package com.github.lincolnstuart.desafiointegradorfirebase.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.lincolnstuart.desafiointegradorfirebase.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}