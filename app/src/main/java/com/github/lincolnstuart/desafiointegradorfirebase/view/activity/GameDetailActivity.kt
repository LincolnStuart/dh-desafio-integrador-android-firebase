package com.github.lincolnstuart.desafiointegradorfirebase.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.github.lincolnstuart.desafiointegradorfirebase.databinding.ActivityGameDetailBinding
import com.github.lincolnstuart.desafiointegradorfirebase.viewmodel.GameDetailViewModel

class GameDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameDetailBinding
    private lateinit var viewmodel: GameDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initComponents()
    }

    private fun initComponents() {
        viewmodel = ViewModelProvider(this).get(GameDetailViewModel::class.java)
        viewmodel.logEventOnAnalytics(this.localClassName)
    }
}