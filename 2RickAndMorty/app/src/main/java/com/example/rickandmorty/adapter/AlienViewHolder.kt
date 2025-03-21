package com.example.rickandmorty.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.model.Character

class AlienViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val characterImage: ImageView = view.findViewById(R.id.characterImage)
    private val characterName: TextView = view.findViewById(R.id.characterName)
    private val characterSpecies: TextView = view.findViewById(R.id.characterSpecies)

    fun bind(character: Character) {
        characterName.text = character.name
        characterSpecies.text = "Species: ${character.species}"

        Glide.with(itemView.context)
            .load(character.image)
            .into(characterImage)
    }
}
