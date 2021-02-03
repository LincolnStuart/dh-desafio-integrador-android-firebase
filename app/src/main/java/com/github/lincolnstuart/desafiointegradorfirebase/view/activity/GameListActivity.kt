package com.github.lincolnstuart.desafiointegradorfirebase.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.github.lincolnstuart.desafiointegradorfirebase.databinding.ActivityGameListBinding
import com.github.lincolnstuart.desafiointegradorfirebase.model.game.Game
import com.github.lincolnstuart.desafiointegradorfirebase.util.Constants.GAME_FIELD_CREATED_AT
import com.github.lincolnstuart.desafiointegradorfirebase.util.Constants.GAME_FIELD_DESCRIPTION
import com.github.lincolnstuart.desafiointegradorfirebase.util.Constants.GAME_FIELD_NAME
import com.github.lincolnstuart.desafiointegradorfirebase.util.Constants.GAME_FIELD_OWNER
import com.github.lincolnstuart.desafiointegradorfirebase.view.adapter.GameListAdapter
import com.github.lincolnstuart.desafiointegradorfirebase.viewmodel.GameListViewModel
import com.google.firebase.firestore.DocumentSnapshot

class GameListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameListBinding
    private lateinit var viewmodel: GameListViewModel
    private val games = mutableListOf<Game>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initComponents()
    }

    private fun initComponents() {
        viewmodel = ViewModelProvider(this).get(GameListViewModel::class.java)
        viewmodel.logEventOnAnalytics(this.localClassName)
        setupObservers()
        viewmodel.getGames()
        binding.fabGameListAdd.setOnClickListener {
            startActivity(Intent(this, GameEditorActivity::class.java))
        }
        binding.rvGameListGames.apply {
            layoutManager = GridLayoutManager(this@GameListActivity, 2)
            adapter = GameListAdapter(games, {
                viewmodel.getThumbnailReference(it)
            }, {
                goToGameDetail(it)
            })
        }
    }

    private fun goToGameDetail(it: Game) {
        val intent = Intent(this@GameListActivity, GameDetailActivity::class.java)
        intent.putExtra(GAME_KEY, it)
        startActivity(intent)
    }

    private fun setupObservers() {
        viewmodel.documentsSnapshotListLiveData.observe(this) {
            Log.i("OBSERVER", "onSuccess: ${it.size} ")
            games.clear()
            it.forEach { document ->
                games.add(getGame(document))
            }
            binding.rvGameListGames.adapter?.notifyDataSetChanged()
        }
        viewmodel.errorMessageLiveData.observe(this) {
            Log.i("OBSERVER", "onFailure: $it ")
        }
    }

    private fun getGame(document: DocumentSnapshot) =
        Game(
            document.data?.get(GAME_FIELD_NAME).toString(),
            document.data?.get(GAME_FIELD_CREATED_AT).toString(),
            document.data?.get(GAME_FIELD_DESCRIPTION).toString(),
            document.data?.get(GAME_FIELD_OWNER).toString(),
            document.id
        )

    companion object{
        const val GAME_KEY = "game"
    }
}