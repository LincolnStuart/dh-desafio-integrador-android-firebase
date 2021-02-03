package com.github.lincolnstuart.desafiointegradorfirebase.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.lincolnstuart.desafiointegradorfirebase.databinding.ItemGameBinding
import com.github.lincolnstuart.desafiointegradorfirebase.model.game.Game
import com.github.lincolnstuart.desafiointegradorfirebase.util.GlideApp
import com.google.firebase.storage.StorageReference

class GameListAdapter(
    private val games: List<Game>,
    private val getThumbnail: (game: Game) -> StorageReference,
    private val onClickItem: (game: Game) -> Unit
) : RecyclerView.Adapter<GameListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(games[position], getThumbnail, onClickItem)
    }

    override fun getItemCount(): Int {
        return games.size
    }

    class ViewHolder(private val binding: ItemGameBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            game: Game,
            getThumbnail: (game: Game) -> StorageReference,
            onClickItem: (game: Game) -> Unit
        ) = with(binding) {
            tvItemGameTitle.text = game.name
            tvItemGameYear.text = game.createdAt
            loadThumbnail(getThumbnail, game)
            itemView.setOnClickListener {
                onClickItem(game)
            }
        }

        private fun ItemGameBinding.loadThumbnail(
            getThumbnail: (game: Game) -> StorageReference,
            game: Game
        ) {
            GlideApp.with(root)
                .load(getThumbnail(game))
                .into(ivItemGameThumbnail)
        }
    }

}