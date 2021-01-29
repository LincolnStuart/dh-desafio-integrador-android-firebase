package com.github.lincolnstuart.desafiointegradorfirebase.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.lincolnstuart.desafiointegradorfirebase.databinding.ActivityGameListBinding

class GameListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}