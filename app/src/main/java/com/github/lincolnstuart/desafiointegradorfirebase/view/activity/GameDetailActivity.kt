package com.github.lincolnstuart.desafiointegradorfirebase.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.github.lincolnstuart.desafiointegradorfirebase.databinding.ActivityGameDetailBinding
import com.github.lincolnstuart.desafiointegradorfirebase.model.game.Game
import com.github.lincolnstuart.desafiointegradorfirebase.util.GlideApp
import com.github.lincolnstuart.desafiointegradorfirebase.view.activity.GameListActivity.Companion.GAME_KEY
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
        loadGameInfo()
        binding.ivGameDetailBackButton.setOnClickListener {
            finish()
        }
    }

    private fun loadGameInfo() {
        val gameExtra = intent.getParcelableExtra<Game>(GAME_KEY)
        gameExtra?.let { game ->
            binding.apply {
                tvGameDetailTitle.text = game.name
                tvGameDetailDescription.text = game.description
                tvGameDetailReleaseDateValue.text = game.createdAt
                GlideApp.with(this@GameDetailActivity)
                    .load(viewmodel.getThumbnailReference(game))
                    .into(ivGameDetailThumbnail)
            }
        }
    }
}