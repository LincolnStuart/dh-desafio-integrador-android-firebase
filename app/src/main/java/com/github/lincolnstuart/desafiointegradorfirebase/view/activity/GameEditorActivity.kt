package com.github.lincolnstuart.desafiointegradorfirebase.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.github.lincolnstuart.desafiointegradorfirebase.databinding.ActivityGameEditorBinding
import com.github.lincolnstuart.desafiointegradorfirebase.model.game.Game
import com.github.lincolnstuart.desafiointegradorfirebase.viewmodel.GameEditorViewModel

class GameEditorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameEditorBinding
    private lateinit var viewmodel: GameEditorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameEditorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initComponents()
    }

    private fun initComponents() {
        viewmodel = ViewModelProvider(this).get(GameEditorViewModel::class.java)
        setupObservers()
        binding.btGameEditorSubmit.setOnClickListener {
            viewmodel.addGame(
                getGame()
            )
        }
    }

    private fun getGame() = Game(
        binding.tilGameEditorName.editText?.text.toString(),
        binding.tilGameEditorCreatedAt.editText?.text.toString(),
        binding.tilGameEditorDescription.editText?.text.toString()
    )

    private fun setupObservers() {
        viewmodel.documentReferenceLiveData.observe(this) {
            Log.i("OBSERVER", "DOCUMENTREFFERENT:${it.id} ")
        }
        viewmodel.errorMessageLiveData.observe(this) {
            Log.i("OBSERVER", "Error: $it ")
        }
    }
}