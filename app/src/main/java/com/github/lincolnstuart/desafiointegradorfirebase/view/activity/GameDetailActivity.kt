package com.github.lincolnstuart.desafiointegradorfirebase.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.lincolnstuart.desafiointegradorfirebase.databinding.ActivityGameDetailBinding

class GameDetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityGameDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}