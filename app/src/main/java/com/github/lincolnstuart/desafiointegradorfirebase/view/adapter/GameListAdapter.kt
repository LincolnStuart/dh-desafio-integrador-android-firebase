package com.github.lincolnstuart.desafiointegradorfirebase.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.lincolnstuart.desafiointegradorfirebase.databinding.ItemGameBinding
import com.github.lincolnstuart.desafiointegradorfirebase.model.game.Game

class GameListAdapter
    (val games: List<Game>) : RecyclerView.Adapter<GameListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(games[position])
    }

    override fun getItemCount(): Int {
        return games.size
    }

    class ViewHolder(private val binding: ItemGameBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(game: Game) = with(binding) {
            tvItemGameTitle.text = game.name
            tvItemGameYear.text = game.createdAt
        }
    }

}