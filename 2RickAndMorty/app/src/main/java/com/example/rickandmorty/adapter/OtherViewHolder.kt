package com.example.rickandmorty.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.model.Character

class OtherViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val characterImage: ImageView = view.findViewById(R.id.characterImage)
    private val characterName: TextView = view.findViewById(R.id.characterName)
    private val characterOrigin: TextView = view.findViewById(R.id.characterOrigin)

    fun bind(character: Character) {
        characterName.text = character.name
        characterOrigin.text = "Origin: ${character.origin.name}"

        Glide.with(itemView.context)
            .load(character.image)
            .into(characterImage)
    }
}
