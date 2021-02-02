package com.github.lincolnstuart.desafiointegradorfirebase.view.activity

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.github.lincolnstuart.desafiointegradorfirebase.databinding.ActivityGameEditorBinding
import com.github.lincolnstuart.desafiointegradorfirebase.model.game.Game
import com.github.lincolnstuart.desafiointegradorfirebase.util.GlideApp
import com.github.lincolnstuart.desafiointegradorfirebase.util.MyGameListGlideModule
import com.github.lincolnstuart.desafiointegradorfirebase.viewmodel.GameEditorViewModel

class GameEditorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameEditorBinding
    private lateinit var viewmodel: GameEditorViewModel
    private var imageUri: Uri? = null

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
            Log.i("OBSERVER", "DOCUMENTREFFERENT:${it.id} ")
        }
        viewmodel.errorMessageLiveData.observe(this) {
            Log.i("OBSERVER", "Error: $it ")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == KEY_PICK_IMAGE) {
            imageUri = data?.data
            binding.civGameEditorThumbnail.visibility = View.VISIBLE
            GlideApp
                .with(this@GameEditorActivity)
                .asBitmap()
                .load(imageUri)
                .dontAnimate()
                .into(binding.civGameEditorThumbnail)
        }
    }

    companion object {
        val KEY_PICK_IMAGE = 1
    }
}