package com.github.lincolnstuart.desafiointegradorfirebase.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.github.lincolnstuart.desafiointegradorfirebase.databinding.ActivityGameListBinding
import com.github.lincolnstuart.desafiointegradorfirebase.viewmodel.GameListViewModel

class GameListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameListBinding
    private lateinit var viewmodel: GameListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initComponents()
    }

    private fun initComponents() {
        viewmodel = ViewModelProvider(this).get(GameListViewModel::class.java)
        viewmodel.logEventOnAnalytics(this.localClassName)
        binding.fabGameListAdd.setOnClickListener {
            startActivity(Intent(this, GameEditorActivity::class.java))
        }
    }
}