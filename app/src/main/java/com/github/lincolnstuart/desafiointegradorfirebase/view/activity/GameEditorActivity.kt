package com.github.lincolnstuart.desafiointegradorfirebase.view.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.github.lincolnstuart.desafiointegradorfirebase.databinding.ActivityGameEditorBinding
import com.github.lincolnstuart.desafiointegradorfirebase.model.game.Game
import com.github.lincolnstuart.desafiointegradorfirebase.util.Constants.DEFAULT_FAILURE_MESSAGE
import com.github.lincolnstuart.desafiointegradorfirebase.util.Constants.DEFAULT_SUCCESS_MESSAGE
import com.github.lincolnstuart.desafiointegradorfirebase.util.GlideApp
import com.github.lincolnstuart.desafiointegradorfirebase.viewmodel.GameEditorViewModel

class GameEditorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameEditorBinding
    private lateinit var viewmodel: GameEditorViewModel
    private var thumbnail: Uri? = null

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

            thumbnail?.let {
                viewmodel.addGame(
                    getGame(), it
                )
            }
        }
        binding.fabGameEditorPickImage.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, KEY_PICK_IMAGE)
        }
    }

    private fun getGame() = Game(
        binding.tilGameEditorName.editText?.text.toString(),
        binding.tilGameEditorCreatedAt.editText?.text.toString(),
        binding.tilGameEditorDescription.editText?.text.toString()
    )

    private fun setupObservers() {
        viewmodel.documentReferenceLiveData.observe(this) {
            Toast.makeText(this@GameEditorActivity, DEFAULT_SUCCESS_MESSAGE, Toast.LENGTH_SHORT).show()
            finish()
        }
        viewmodel.errorMessageLiveData.observe(this) {
            Toast.makeText(this@GameEditorActivity, DEFAULT_FAILURE_MESSAGE, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == KEY_PICK_IMAGE) {
            thumbnail = data?.data
            binding.civGameEditorThumbnail.visibility = View.VISIBLE
            GlideApp
                .with(this@GameEditorActivity)
                .asBitmap()
                .load(thumbnail)
                .dontAnimate()
                .into(binding.civGameEditorThumbnail)
        }
    }

    companion object {
        val KEY_PICK_IMAGE = 1
    }
}