package com.example.rickandmorty.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.model.Character
import com.example.rickandmorty.viewholder.HumanViewHolder
import com.example.rickandmorty.viewholder.AlienViewHolder
import com.example.rickandmorty.viewholder.OtherViewHolder

class CharacterAdapter(private val characters: List<Character>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_HUMAN = 1
        private const val VIEW_TYPE_ALIEN = 2
        private const val VIEW_TYPE_OTHER = 3
    }

    override fun getItemViewType(position: Int): Int {
        return when (characters[position].species) {
            "Human" -> VIEW_TYPE_HUMAN
            "Alien" -> VIEW_TYPE_ALIEN
            else -> VIEW_TYPE_OTHER
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_HUMAN -> HumanViewHolder(inflater.inflate(R.layout.item_character, parent, false))
            VIEW_TYPE_ALIEN -> AlienViewHolder(inflater.inflate(R.layout.item_character, parent, false))
            else -> OtherViewHolder(inflater.inflate(R.layout.item_character, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val character = characters[position]
        when (holder) {
            is HumanViewHolder -> holder.bind(character)
            is AlienViewHolder -> holder.bind(character)
            is OtherViewHolder -> holder.bind(character)
        }
    }

    override fun getItemCount(): Int = characters.size
}
